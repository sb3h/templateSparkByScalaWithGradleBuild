package com.hhh.hello.base

import org.apache.spark.SparkContext
import com.hhh.hello.base.{BaseSparkTempLate, RunnableHasParam}

/**
  * Created by root on 17-1-9.
  */
object SparkTempLateLocalDev extends BaseSparkTempLate {
  def main(args: Array[String]): Unit = {
    //    val appName = null
    val appName = "3H-Spark"

    val realRun = new RunnableHasParam[SparkContext] {
      override def run(sc: SparkContext): Unit = {
        println("this run code")
      }
    }


    println("this is start")
    runSparkCodeOnTemplateByLocal(appName, realRun)
    println("this iu end")
  }

}
