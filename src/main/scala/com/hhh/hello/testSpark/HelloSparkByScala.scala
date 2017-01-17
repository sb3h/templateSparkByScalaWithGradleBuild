package com.hhh.hello.testSpark

import org.apache.spark._

/**
  * Created by huanghh on 2016/12/12.
  */
object HelloSparkByScala {
  def main(args: Array[String]) {
    print("HelloScala ")

    val sConf =  new SparkConf()
//      .setMaster("spark://master-node:7077")//test is not use cluster
      .setMaster("local[4]")
      .setAppName("3H-Spark")
//      .setJars(List("/root/test/helloSpark/build/libs/helloSpark.jar"))
    val sc = new SparkContext(sConf)
    //
//    val rdd = sc.textFile("file:///etc/hosts")
//    //    rdd.foreach(_=>println)
//    println(rdd.first())

    val rdd = sc.parallelize(List(1,2,3,4))
    rdd.foreach(l => {
      val tName = Thread.currentThread().getName
      println(tName +" " + l)
    })
    println("nimei:"+rdd.count)
  }
}
