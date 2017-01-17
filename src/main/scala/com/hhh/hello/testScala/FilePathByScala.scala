package com.hhh.hello.testScala

import java.io.File

import scala.collection.mutable.ListBuffer

/**
  * Created by root on 16-12-20.
  */
object FilePathByScala {
  def main(args: Array[String]): Unit = {
    val jarDir: File = new File("/export/servers/spark/jars")
    val jarNames = ListBuffer[String]()

    val jarFiles: Array[File] = jarDir.listFiles
    var i: Int = 0
    while (i < jarFiles.length) {
      {
        val jarFile: File = jarFiles(i)

        jarNames.insert(i,jarFile.getAbsolutePath)
      }
      {
        i += 1; i - 1
      }
    }

    print(jarNames)
  }
}
