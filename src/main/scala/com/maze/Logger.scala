package com.maze

object Logger {
  def logEventConsumed(user: User, event: Event): Unit = {
    val id = user.id
    println(s"User $id has consumed event: $event")
  }

  def logExceptionAcceptingUser(ex: Throwable): Unit = {
    println(s"Caught exception while accepting users: $ex")
  }

  def logUserAccepted(line: String): Unit = {
    println(s"Accepted user: $line")
  }

  def logUserClientProcessorStarted(): Unit = {
    println("Start accepting users.")
  }

  def logExceptionNotifyFollowers(ex: Throwable): Unit = {
    println(s"Notifying followers has thrown: $ex")
  }

  def logExceptionIncomingEvent(ex: Throwable): Unit = {
    println(s"Caught exception while processing incoming events: $ex")
  }

  def logIncomingEventProcessorStarted(): Unit = {
    println("Start receiving events.")
  }

  def logEventRaised(event: Event): Unit = {
    println(s"Event raised: $event")
  }

  def logExceptionProcessEvent(ex: Throwable): Unit = {
    println(s"Exception while raising event: $ex")
  }

  def logEventQueueProcessorStarted(): Unit = {
    println("Start processing event queue.")
  }

  def logUnknownEventType(parts: Array[String]): Unit = {
    println(s"Cannot parse unknown event type: $parts!")
  }

  def logExceptionAcceptingUsers(exception: Throwable): Unit = {
    println(s"Caught exception while accepting user connections: $exception")
  }

  def logExceptionAcceptingIncomingEvents(exception: Throwable): Unit = {
    println(s"Caught exception while accepting incoming events: $exception")
  }
}
