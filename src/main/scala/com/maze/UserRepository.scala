package com.maze

import java.io.{OutputStream, PrintStream}
import java.net.Socket
import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters._

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
  var users: ConcurrentHashMap[Int, User] = new ConcurrentHashMap[Int, User]()

  def add(id: Int, socket: Socket): Unit = {
    val stream = PrintStreamCreator.fromSocket(socket)
    add(id, stream)
  }

  def add(id: Int, stream: PrintStream): User = {
    val user = new User(id, stream)
    users.put(id, user)
    user
  }

  def addDummy(id: Int): User = {
    val stream = PrintStreamCreator.dummy
    add(id, stream)
  }

  def get(id: Int): User = {
    if (!users.contains(id)){
      addDummy(id)
    }
    users.get(id)
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
  }.asScala
}
