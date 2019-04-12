package com.maze

import java.io.{OutputStreamWriter, PrintWriter}
import java.net.Socket

import scala.collection.immutable.HashMap

class UserRepository {
  var users: HashMap[Int, User] = HashMap[Int, User]()

  def add(id: Int, socket: Socket): Unit = {
    val stream = socket.getOutputStream();
    val writer = new OutputStreamWriter(stream);
    add(id, new PrintWriter(writer))
  }

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
