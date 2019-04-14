package com.maze

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    var sequenceNumber = 1
    while (true) {
      try {
        if (eventQueue.nonEmpty) {
          val event = eventQueue.peek
          if (event != null && event.sequenceNumber <= sequenceNumber) {
            //event = eventQueue.dequeue()
            sequenceNumber = sequenceNumber + 1
            println(s"sequence number: $sequenceNumber")
            //event.raiseEvent(userRepository)
            //print("Raised event ")
            //println(event)
          }
        }
      } catch {
        case ex: RuntimeException => {
          println(s"Exception while raising event: $ex")
        }
      }
    }
  }
}
