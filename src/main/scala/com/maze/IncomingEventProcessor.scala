package com.maze

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.Socket

class IncomingEventProcessor(socket: Socket, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    try {
      val stream = socket.getInputStream()
      val reader = new InputStreamReader(stream)
      val bufferedReader = new BufferedReader(reader)
      while (true) {
        val line = bufferedReader.readLine()
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
