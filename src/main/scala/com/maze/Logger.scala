package com.maze

object Logger {
  def logUnknownEventType(parts: Array[String]): Unit = {
    println(s"Cannot parse unknown event type: $parts!")
  }

  def logAccpetingUserException(exception: Throwable): Unit = {
    //println(s"Caught exception while accepting user connections: $exception")
  }

  def logAcceptingIncomingEventsException(exception: Throwable): Unit = {
    //println(s"Caught exception while accepting incoming events: $exception")
  }
}
