package com.maze

import java.io.{OutputStream, PrintStream}
import java.net.Socket

import scala.collection.immutable.HashMap

object PrintStreamCreator {
  def fromSocket(socket: Socket) : PrintStream = {
    val stream = socket.getOutputStream
    new PrintStream(stream)
  }

  def fromStream(stream: OutputStream) : PrintStream = {
    new PrintStream(stream)
  }

  def dummy() : PrintStream = {
    new PrintStream(System.out)
  }
}

class UserRepository {
  var users: HashMap[Int, User] = HashMap[Int, User]()

  def add(id: Int, socket: Socket): Unit = {
    val stream = PrintStreamCreator.fromSocket(socket)
    add(id, stream)
  }

  def add(id: Int, stream: PrintStream): User = {
    val user = new User(id, stream)
    users += (id -> user)
    user
  }

  def addDummy(id: Int): User = {
    val stream = PrintStreamCreator.dummy
    add(id, stream)
  }

  def get(id: Int): User = {
    users.get(id) match {
      case Some(user) => user
      case None => addDummy(id)
    }
  }

  def follow(fromId: Int, toId: Int, event: FollowEvent): Unit = {
    val to = get(toId)
    to.addFollower(fromId)
    to.consume(event)
  }

  def unfollow(fromId: Int, toId: Int): Unit = {
    val to = get(toId)
    to.removeFollower(fromId)
  }

  def allUsers(): Iterable[User] = {
    users.values
  }
}
