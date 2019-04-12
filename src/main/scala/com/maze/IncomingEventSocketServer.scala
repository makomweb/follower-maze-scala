package com.maze

import java.io.IOException
import java.net.ServerSocket
import java.util.concurrent.ExecutorService

class IncomingEventSocketServer(serverSocket: ServerSocket, threadPool: ExecutorService, eventQueue: EventQueue) extends Runnable {
  override def run(): Unit = {
    while (true) {
      try {
        val socket = serverSocket.accept()
        val processor: IncomingEventProcessor = new IncomingEventProcessor(socket, eventQueue)
        threadPool.submit(processor)
      } catch {
        case ex: IOException => {
          println("Caught exception while accepting incoming events!")
          println(ex)
        }
      }
    }
  }
}
