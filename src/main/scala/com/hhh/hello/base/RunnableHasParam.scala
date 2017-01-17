package com.hhh.hello.base

/**
  * Created by root on 17-1-13.
  */
trait RunnableHasParam[T] {
  def run(sc: T)
}
