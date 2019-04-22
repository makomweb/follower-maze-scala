package com.maze

import java.net.Socket
import java.util.concurrent.atomic.AtomicBoolean

class UserClientProcessor(socket: Socket, userRepository: UserRepository, wasCancelled: AtomicBoolean) extends Runnable {
  override def run(): Unit = {
    Logger.logUserClientProcessorStarted()
    try {
      val reader = ReaderCreator.fromSocket(socket)
      while (!wasCancelled.get) {
        val line = reader.readLine()
        if (line != null) {
          userRepository.add(line.toInt, socket, false)
          Logger.logUserAccepted(line)
        }
      }
    } catch {
      case ex: Throwable => {
        Logger.logExceptionAcceptingUser(ex)
      }
    }
  }
}
