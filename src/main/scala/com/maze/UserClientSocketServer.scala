package com.maze

import java.net.ServerSocket
import java.util.concurrent.ExecutorService

class UserClientSocketServer(serverSocket: ServerSocket, threadPool: ExecutorService, userRepository: UserRepository) extends Runnable {
  override def run(): Unit = {
    while (true) {
      try {
        val socket = serverSocket.accept
        val processor: UserClientProcessor = new UserClientProcessor(socket, userRepository)
        threadPool.submit(processor)
      } catch {
        case ex: Throwable => {
          Logger.logAccpetingUserException(ex)
        }
      }
    }
  }
}
