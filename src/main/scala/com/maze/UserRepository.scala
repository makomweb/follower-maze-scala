package com.maze

import java.io.PrintWriter

import scala.collection.immutable.HashMap

object UserRepository {
  var users: HashMap[Int, User] = HashMap[Int, User]()

  def add(id: Int, writer: PrintWriter): User = {
    val user = new User(id, writer)
    users += (id -> user)
    user
  }

  def addDummy(id: Int): User = {
    val writer = new PrintWriter(System.out)
    add(id, writer)
  }

  def get(id: Int): User = {
    users.get(id) match {
      case Some(user) => user
      case None => addDummy(id)
    }
  }
}
