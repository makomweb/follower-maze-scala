package com.maze

import java.io.PrintWriter

import scala.collection.mutable.HashSet

class User(val id: Int, writer: PrintWriter) {
  val followers = HashSet[Int]()

  def addFollower(id: Int): Unit = {
    followers.add(id)
  }

  def removeFollower(id: Int): Unit = {
    followers.remove(id)
  }

  def consume(event: Event): Unit = {
    writer.print(event)
    writer.flush()
  }
}
