package com.maze

import java.io.{ByteArrayOutputStream}

import org.scalatest.FunSuite

class UserTests extends FunSuite{
  test("Writing event should succeed") {
    val byteStream = new ByteArrayOutputStream
    val stream = PrintStreamCreator.fromStream(byteStream)
    val user = new User(1, stream)
    user.consume(FollowEvent(44, 22, 11))

    val bytes = byteStream.toByteArray
    val str = new String(bytes)

    assert("44|F|22|11\r\n" == str)
  }
}
