package com.maze

import java.io.{OutputStream, PrintWriter}
import java.net.Socket

object PrintWriterCreator {
  def fromSocket(socket: Socket) : PrintWriter = {
    val stream = socket.getOutputStream
    fromStream(stream)
  }

  def fromStream(stream: OutputStream) : PrintWriter = {
    new PrintWriter(stream)
  }

  def nullWriter() : PrintWriter = {
    val stream = new OutputStream {
      override def write(b: Int): Unit = {
        /* do nothing */
      }
    }
    new PrintWriter(stream)
  }
}
