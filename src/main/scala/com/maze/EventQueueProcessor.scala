package com.maze

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    var sequenceNumber = 1
    while (true) {
      //Thread.sleep(10L)
      //print(".")
      try {
        if (eventQueue.nonEmpty) {
          /*
          val event = eventQueue.dequeue
          sequenceNumber = sequenceNumber + 1
          */
          var event = eventQueue.peek
          if (event != null && event.sequenceNumber <= sequenceNumber) {
            event = eventQueue.dequeue
            //println(s"sequence number: $sequenceNumber")
            sequenceNumber = sequenceNumber + 1
            event.raiseEvent(userRepository)
          }
          //event.raiseEvent(userRepository)
          //println (s"Raised event: $event")
        }
      } catch {
        case ex: Throwable => {
          println(s"Exception while raising event: $ex")
        }
      }
    }
  }
}
