package com.maze

import java.io.{ByteArrayOutputStream, OutputStreamWriter, PrintWriter}

import org.scalatest.FunSuite

class UserTests extends FunSuite{
  test("Writing event should succeed") {
    val stream = new ByteArrayOutputStream()
    val writer = new PrintWriter(new OutputStreamWriter(stream))
    val user = new User(1, writer)
    user.consume(FollowEvent(44, 22, 11))

    val bytes = stream.toByteArray()
    val str = new String(bytes)

    assert("44|F|22|11\r\n" == str)
  }
}
