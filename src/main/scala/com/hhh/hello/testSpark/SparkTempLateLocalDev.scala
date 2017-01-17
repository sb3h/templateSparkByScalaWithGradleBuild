package com.hhh.hello.testSpark

import com.hhh.hello.base.{BaseSparkTempLate, RunnableHasParam}
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by root on 17-1-9.
  */
object SparkWindowWordCountLocalDev extends BaseSparkTempLate {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      System.err.println("Usage:<socketServerIp> <port>")
      System.exit(1)
    }

    val socketServerIp = args(0).toString
    val port = args(1).toInt

    //    val appName = null
    val appName = "SparkStatefulWordCountLocalDev$"

    val realRun = new RunnableHasParam[SparkContext] {
      override def run(sc: SparkContext): Unit = {

        val seconds = Seconds(5);

        val ssc = new StreamingContext(sc, seconds)

        ssc.checkpoint(".")

        val lines = ssc.socketTextStream(
          socketServerIp, port,
          StorageLevel.MEMORY_ONLY_SER
        )
        val words = lines.flatMap(_.split(","))
        val wordCountsMap = words.map(x => (x, 1))
        //        println("123:"+wordCountsMap)

        //使用updateStateByKey来更新状态
        val stateDStream = wordCountsMap.reduceByKeyAndWindow(
          (a:Int,b:Int)=>(a+b) ,
          seconds*2,//there must be multiple to batchDuration
          seconds*2//move time
        )

        stateDStream.print()
        ssc.start()
        ssc.awaitTermination()
      }
    }


    println("this is start")
    runSparkCodeOnTemplateByLocal(appName, realRun)
    println("this iu end")
  }

}
