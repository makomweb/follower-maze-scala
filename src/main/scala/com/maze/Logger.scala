package com.maze

object Logger {
  def logAccpetingUserException(exception: Throwable): Unit = {
    println(s"Caught exception while accepting user connections: $exception")
  }

  def logAcceptingIncomingEventsException(exception: Throwable): Unit = {
    println(s"Caught exception while accepting incoming events: $exception")
  }
}
