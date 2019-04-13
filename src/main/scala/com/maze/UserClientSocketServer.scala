package com.maze

import java.io.IOException
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
        case ex: IOException => {
          //println("Caught exception while accepting user connections!")
          //println(ex)
        }
      }
    }
  }
}
