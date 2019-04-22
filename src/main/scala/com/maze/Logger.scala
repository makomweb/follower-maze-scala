package com.maze

object Logger {
  def logExceptionAcceptingUser(ex: Throwable): Unit = {
    println(s"Caught exception while accepting users: $ex")
  }

  def logUserAccepted(line: String) = {
    println(s"accepted user $line")
  }

  def logUserClientProcessorStarted() = {
    println("Start accepting users.")
  }

  def logExceptionNotifyFollowers(ex: Throwable): Unit = {
    println(s"notifyFollowers() has thrown: $ex")
  }

  def logExceptionIncomingEvent(ex: Throwable): Unit = {
    println(s"Caught exception while processing incoming events: $ex")
  }

  def logIncomingEventProcessorStarted() = {
    println("Start receiving events.")
  }

  def logEventRaised(event: Event) = {
    println(s"Event raised: $event")
  }

  def logExceptionProcessEvent(ex: Throwable) = {
    println(s"Exception while raising event: $ex")
  }

  def logEventQueueProcessorStarted(): Unit = {
    println("Start processing event queue.")
  }

  def logUnknownEventType(parts: Array[String]): Unit = {
    println(s"Cannot parse unknown event type: $parts!")
  }

  def logExceptionAccptingUsers(exception: Throwable): Unit = {
    //println(s"Caught exception while accepting user connections: $exception")
  }

  def logExceptionAcceptingIncomingEvents(exception: Throwable): Unit = {
    //println(s"Caught exception while accepting incoming events: $exception")
  }
}
