package com.hhh.hello.base

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 17-1-9.
  */
class BaseSparkTempLate {
  def runSparkCodeOnTemplate(appName: String, master: String, realRun: RunnableHasParam[SparkContext] with Object {def run(t: SparkContext): Unit}): Unit = {
    if(appName==null) return
    if(master==null) return
    if(realRun==null) return

    val sConf = new SparkConf()
      //      .setMaster("spark://master-node:7077")//test is not use cluster
      .setMaster(master)
      .setAppName(appName)
    val sc = new SparkContext(sConf)

    realRun.run(sc)

    sc.stop()
  }

  def runSparkCodeOnTemplateByLocal(appName: String, realRun: RunnableHasParam[SparkContext] with Object {def run(t: SparkContext): Unit}): Unit = {
    if(appName==null) return
    if(realRun==null) return
    val master = "local[4]"

    runSparkCodeOnTemplate(appName,master,realRun)
  }


  def runSparkCodeOnTemplateByMaster(appName: String, realRun: RunnableHasParam[SparkContext] with Object {def run(t: SparkContext): Unit}): Unit = {
    if(appName==null) return
    if(realRun==null) return
    val master = "spark://"

    runSparkCodeOnTemplate(appName,master,realRun)
  }
}


