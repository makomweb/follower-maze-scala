package com.maze

import java.io.IOException
import java.net.Socket
import java.util.concurrent.atomic.AtomicBoolean

class UserClientProcessor(socket: Socket, userRepository: UserRepository, wasCancelled: AtomicBoolean) extends Runnable {
  override def run(): Unit = {
    println("Start accepting users.")
    try {
      val reader = ReaderCreator.fromSocket(socket)
      while (!wasCancelled.get) {
        val line = reader.readLine()
        if (line != null) {
          userRepository.add(line.toInt, socket, false)
          println(s"accepted user $line")
        }
      }
    } catch {
      case ex: IOException => {
        println(s"Caught exception while processing accepted users: $ex")
      }
      case ex: RuntimeException => {
        println(s"Could not add accepted user: $ex")
      }
    }
  }
}
