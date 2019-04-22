package com.maze

import java.net.Socket
import java.util.concurrent.atomic.AtomicBoolean

class IncomingEventProcessor(socket: Socket, eventQueue: EventQueue, wasCancelled: AtomicBoolean) extends Runnable {
  override def run(): Unit = {
    try {
      val reader = ReaderCreator.fromSocket(socket)
      while (!wasCancelled.get) {
        val line = reader.readLine
        if (line != null) {
          val event = EventDeserializer.deserialize(line)
          eventQueue.enqueue(event)
        }
      }
    } catch {
      case ex: Throwable => {
        Logger.logExceptionIncomingEvent(ex)
      }
    }
  }
}
