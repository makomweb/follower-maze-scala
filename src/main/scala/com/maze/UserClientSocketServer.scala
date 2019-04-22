package com.maze

import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.atomic.AtomicBoolean

class UserClientSocketServer(serverSocket: ServerSocket, threadPool: ExecutorService, userRepository: UserRepository, wasCancelled: AtomicBoolean) extends Runnable {
  override def run(): Unit = {
    Logger.logUserClientProcessorStarted()
    while (!wasCancelled.get) {
      try {
        val socket = serverSocket.accept
        val processor: UserClientProcessor = new UserClientProcessor(socket, userRepository, wasCancelled)
        threadPool.submit(processor)
      } catch {
        case ex: Throwable => {
          Logger.logExceptionAcceptingUsers(ex)
        }
      }
    }
  }
}
