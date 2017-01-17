package com.hhh.hello.testSpark

import java.io.PrintWriter
import java.net.ServerSocket

import scala.io.Source
import scala.util.Random

/**
  * Created by root on 17-1-16.
  */
object SaleSimulation {


  def main(args: Array[String]): Unit = {
    if(args.length!=3){
      System.err.println("Usage:<filename> <port> <sleepMillisecond>")
      System.exit(1)
    }

    val fileName = args(0)
    val lines = Source.fromFile(fileName).getLines().toList
    val fileRow = lines.length

    val port = args(1)
    val sleepMillisecond = args(2)

    val listener = new ServerSocket(port.toInt)

    while(true){
      val socket = listener.accept();
      new Thread(){
        override def run(): Unit = {
          println("Got client connected from: "+ socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream,true)
          while(true){
            Thread.sleep(sleepMillisecond.toLong)
            val rdmLineIndex: Int = getRdmLineIndex(fileRow)
            val content = lines(rdmLineIndex)
            println(content)
            out.write(content+"\n")
            out.flush()
          }
          socket.close()
        }
      }.start()
    }
  }

  def getRdmLineIndex(fileRow: Int): Int = {
    val rdm = new Random
    val rdmLineIndex = rdm.nextInt(fileRow)
    rdmLineIndex
  }
}
