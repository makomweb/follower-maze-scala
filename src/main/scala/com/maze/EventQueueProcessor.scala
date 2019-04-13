package com.maze

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    var sequenceNumber = 1
    while (true) {
      try {
        if (eventQueue.nonEmpty) {
          var event = eventQueue.peek
          if (event != null && event.sequenceNumber <= sequenceNumber) {
            event = eventQueue.dequeue()
            sequenceNumber = sequenceNumber + 1
            event.raiseEvent(userRepository)
            print("Raised event ")
            println(event)
          }
        }
      } catch {
        case ex: RuntimeException => {
          println("Exception while raising event!")
          println(ex)
        }
      }
    }
  }
}
