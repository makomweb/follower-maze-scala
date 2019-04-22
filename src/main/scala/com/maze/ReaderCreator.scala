package com.maze

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

object ReaderCreator {
  def fromSocket(socket: Socket) : BufferedReader = {
    val stream = socket.getInputStream()
    val reader = new InputStreamReader(stream)
    new BufferedReader(reader)
  }
}
