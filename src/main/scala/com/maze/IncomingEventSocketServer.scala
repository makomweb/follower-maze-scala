package com.maze

import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.atomic.AtomicBoolean

class IncomingEventSocketServer(serverSocket: ServerSocket, threadPool: ExecutorService, eventQueue: EventQueue, wasCancelled: AtomicBoolean) extends Runnable {
  override def run(): Unit = {
    Logger.logIncomingEventProcessorStarted()
    while (!wasCancelled.get) {
      try {
        val socket = serverSocket.accept
        val processor: IncomingEventProcessor = new IncomingEventProcessor(socket, eventQueue, wasCancelled)
        threadPool.submit(processor)
      } catch {
        case ex: Throwable => {
          Logger.logExceptionAcceptingIncomingEvents(ex)
        }
      }
    }
  }
}
