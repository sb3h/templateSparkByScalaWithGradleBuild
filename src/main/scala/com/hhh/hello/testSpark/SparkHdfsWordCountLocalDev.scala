package com.hhh.hello.testSpark

import com.hhh.hello.base.{BaseSparkTempLate, RunnableHasParam}
import org.apache.spark.SparkContext
import org.apache.spark.streaming.dstream.{DStream, PairDStreamFunctions}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by root on 17-1-9.
  */
object SparkHdfsWordCountLocalDev extends BaseSparkTempLate {
  def main(args: Array[String]): Unit = {
    //    val appName = null
    val appName = "3H-Spark"

    val realRun = new RunnableHasParam[SparkContext] {
      override def run(sc: SparkContext): Unit = {
//        println("this run code")

        val ssc = new StreamingContext(sc,Seconds(20))
        val lines = ssc.textFileStream("/test/spark_streaming/temp")
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
