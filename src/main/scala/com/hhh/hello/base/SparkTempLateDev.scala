package com.hhh.hello.base

import org.apache.spark.SparkContext

/**
  * Created by root on 17-1-9.
  */
object SparkTempLateDev extends BaseSparkTempLate {
  def main(args: Array[String]): Unit = {
    //    val appName = null
    val appName = "3H-Spark"
    val master = "local[4]"

    val realRun = new RunnableHasParam[SparkContext] {
      override def run(t: SparkContext): Unit = {
        println("this run code")
      }
    }
    println("this is start")
    runSparkCodeOnTemplate(appName, master, realRun)
    println("this iu end")
  }

}
