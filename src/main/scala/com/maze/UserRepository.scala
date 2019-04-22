package com.maze

import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.ConcurrentHashMap

import scala.collection.JavaConverters._

class UserRepository {
  var users: ConcurrentHashMap[Int, User] = new ConcurrentHashMap[Int, User]()

  def add(id: Int, socket: Socket, dummy: Boolean): Unit = {
    val stream = PrintWriterCreator.fromSocket(socket)
    add(id, stream, dummy)
  }

  def add(id: Int, stream: PrintWriter, dummy: Boolean): User = {
    val user = new User(id, stream, dummy)
    users.put(id, user)
    user
  }

  def addDummy(id: Int): User = {
    val stream = PrintWriterCreator.nullWriter
    add(id, stream, true)
  }

  def get(id: Int): User = {
    if (!users.containsKey(id)){
      addDummy(id)
    }
    users.get(id)
  }

  def follow(fromId: Int, toId: Int, event: FollowEvent): Unit = {
    val to = get(toId)
    to.addFollower(fromId)
    to.consume(event)
    Logger.logEventConsumed(to, event)
  }

  def unfollow(fromId: Int, toId: Int): Unit = {
    val to = get(toId)
    to.removeFollower(fromId)
    Logger.logEventConsumed(to, event)
  }

  def allUsers(): Iterable[User] = {
    users.values
  }.asScala
}
