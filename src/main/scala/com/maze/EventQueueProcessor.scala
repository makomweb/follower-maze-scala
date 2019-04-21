package com.maze

import java.util.concurrent.atomic.AtomicBoolean

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue, wasCancelled: AtomicBoolean) extends Runnable {
  var sequenceNumber: Int = 1

  override def run(): Unit = {
    println("Start processing event queue.")
    while (!wasCancelled.get) {
      process
    }
  }

  def processQueue(): Unit = {
    while (eventQueue.peek != null) {
      process
    }
  }

  def process = {
    try {
      eventQueue.synchronized {
        var event = eventQueue.peek
        if (event != null && event.sequenceNumber <= sequenceNumber) {
          event = eventQueue.dequeue
          sequenceNumber = sequenceNumber + 1
          event.raiseEvent(userRepository)
        }
      }
    } catch {
      case ex: Throwable => {
        println(s"Exception while raising event: $ex")
      }
    }
  }
}
