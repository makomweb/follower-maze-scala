package com.maze

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.Socket

class IncomingEventProcessor(socket: Socket, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    try {
      val reader = ReaderCreator.fromSocket(socket)
      while (true) {
        val line = reader.readLine()
        //print("Read event line: ")
        //println(line)

        if (line != null) {
          val event = EventDeserializer.deserialize(line)
          eventQueue.enqueue(event)
          print("enqueued event ")
          println(event)
        }
      }
    } catch {
      case ex: IOException => {
        println("Caught exception while processing incoming events!")
        println(ex)
      }
    }
  }
}

object ReaderCreator {
  def fromSocket(socket: Socket) : BufferedReader = {
    val stream = socket.getInputStream()
    val reader = new InputStreamReader(stream)
    new BufferedReader(reader)
  }
}
