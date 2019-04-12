package com.maze

class EventQueueProcessor(userRepository: UserRepository, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    while (true) {
      if (eventQueue.nonEmpty) {
        val event = eventQueue.dequeue()
        try {
          event.raiseEvent(userRepository)
        } catch {
          case ex: RuntimeException => {
            println("Exception while raising event!")
            println(ex)
          }
        }
      }
    }
  }
}
