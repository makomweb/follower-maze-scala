package com.maze

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.Socket

class UserClientProcessor(socket: Socket, userRepository: UserRepository) extends Runnable {
  override def run(): Unit = {
    try {
      val stream = socket.getInputStream()
      val reader = new InputStreamReader(stream)
      val bufferedReader = new BufferedReader(reader)
      while (true) {
        val line = bufferedReader.readLine()
        if (line != null) {
          userRepository.add(line.toInt, socket)
          //print("accepted user ")
          //println(line)
        }
      }
    } catch {
      case ex: IOException => {
        //println("Caught exception while processing accepted users!")
        //println(ex)
      }
      case ex: RuntimeException => {
        //println("Could not add accepted user!")
        //println(ex)
      }
    }
  }
}
