package com.maze

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.Socket

class IncomingEventProcessor(socket: Socket, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    println("Start receiving events.")
    try {
      val reader = ReaderCreator.fromSocket(socket)
      while (true) {
        val line = reader.readLine
        if (line != null) {
          //println(s"Received event : $line")
          val event = EventDeserializer.deserialize(line)
          eventQueue.enqueue(event)
        }
      }
    } catch {
      case ex: IOException => {
        println(s"Caught exception while processing incoming events: $ex")
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
