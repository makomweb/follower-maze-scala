package com.maze

import java.io.{ IOException }
import java.net.Socket

class UserClientProcessor(socket: Socket, userRepository: UserRepository) extends Runnable {
  override def run(): Unit = {
    try {
      val reader = ReaderCreator.fromSocket(socket)
      while (true) {
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
