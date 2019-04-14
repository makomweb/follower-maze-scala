package com.maze

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    var sequenceNumber = 1
    while (true) {
      Thread.sleep(1000L)
      print(".")
      try {
        if (eventQueue.nonEmpty) {
          val event = eventQueue.dequeue
          println(s"sequence number: $sequenceNumber")
          sequenceNumber = sequenceNumber + 1
          /*
          val event = eventQueue.peek
          if (event != null && event.sequenceNumber <= sequenceNumber) {
            //event = eventQueue.dequeue()
            println(s"sequence number: $sequenceNumber")
            sequenceNumber = sequenceNumber + 1
            //event.raiseEvent(userRepository)
            //print("Raised event ")
            //println(event)
          }
          */
        }
      } catch {
        case ex: Throwable => {
          println(s"Exception while raising event: $ex")
        }
      }
    }
  }
}
