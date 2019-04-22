package com.maze

import java.io.{OutputStream, PrintWriter}
import java.net.Socket

object PrintStreamCreator {
  def fromSocket(socket: Socket) : PrintWriter = {
    val stream = socket.getOutputStream
    new PrintWriter(stream)
  }

  def fromStream(stream: OutputStream) : PrintWriter = {
    new PrintWriter(stream)
  }

  def dummy() : PrintWriter = {
    //new PrintWriter(System.out)
    val stream = new OutputStream {
      override def write(b: Int): Unit = { /* do nothing */ }
    }
    new PrintWriter(stream)
  }
}
