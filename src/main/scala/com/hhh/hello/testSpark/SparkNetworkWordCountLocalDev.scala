package com.hhh.hello.testSpark

import com.hhh.hello.base.{BaseSparkTempLate, RunnableHasParam}
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by root on 17-1-9.
  */
object SparkNetworkWordCountLocalDev extends BaseSparkTempLate {
  def main(args: Array[String]): Unit = {
    if(args.length!=2){
      System.err.println("Usage:<socketServerIp> <port>")
      System.exit(1)
    }

    val socketServerIp = args(0).toString
    val port = args(1).toInt

    //    val appName = null
    val appName = "SparkNetworkWordCountLocalDev"

    val realRun = new RunnableHasParam[SparkContext] {
      override def run(sc: SparkContext): Unit = {
        val ssc = new StreamingContext(sc,Seconds(5))

        val lines = ssc.socketTextStream(
          socketServerIp,port,
          StorageLevel.MEMORY_AND_DISK_SER
        )
        val words = lines.flatMap(_.split(" "))
        val wordCountsMap = words.map(x => (x,1))
        //        println("123:"+wordCountsMap)

        val wordCounts = wordCountsMap.reduceByKey(_+_)

        wordCounts.print()

        ssc.start()
        ssc.awaitTermination()
      }
    }


    println("this is start")
    runSparkCodeOnTemplateByLocal(appName, realRun)
    println("this iu end")
  }

}
