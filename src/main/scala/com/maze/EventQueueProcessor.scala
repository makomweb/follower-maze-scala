package com.maze

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue) extends Runnable {
  var sequenceNumber: Int = 1

  override def run(): Unit = {
    Thread.sleep(3000L)

    println("Start processing event queue.")
    while (true) {
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
      var event = eventQueue.peek
      if (event != null && event.sequenceNumber <= sequenceNumber) {
        event = eventQueue.dequeue
        sequenceNumber = sequenceNumber + 1
        event.raiseEvent(userRepository)
      }
    } catch {
      case ex: Throwable => {
        println(s"Exception while raising event: $ex")
      }
    }
  }
}
