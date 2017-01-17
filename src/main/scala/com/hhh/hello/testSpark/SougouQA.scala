package com.hhh.hello.testSpark

import com.hhh.hello.base.{BaseSparkTempLate, RunnableHasParam}
import org.apache.spark.SparkContext

/**
  * Created by root on 17-1-9.
  */
object SougouQA extends BaseSparkTempLate {
  def main(args: Array[String]): Unit = {
    //    val appName = null
    val appName = "3H-Spark"
    val master = "local[4]"

    val realRun = new RunnableHasParam[SparkContext] {
      override def run(t: SparkContext): Unit = {
        print("this run code")
      }
    }
    println("this is start")
    runSparkCodeOnTemplate(appName, master, realRun)
    println("this iu end")
  }

}
