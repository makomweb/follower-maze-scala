package com.maze

import java.io.PrintStream

class User(val id: Int, out: PrintStream) {
  val followerIds = createSet[Int]

  def addFollower(id: Int): Unit = {
    followerIds.add(id)
  }

  def removeFollower(id: Int): Unit = {
    followerIds.remove(id)
  }

  def consume(event: Event): Unit = {
    out.println(event)
    println(s"Consumed event: $event")
  }

  def notifyFollowers(event: StatusUpdateEvent, userRepository: UserRepository): Unit = {
    followerIds.foreach(followerId => {
      val follower = userRepository.get(followerId)
      follower.consume(event)
    })
  }

  def createSet[T]() = {
    import scala.collection.JavaConverters._
    java.util.Collections.newSetFromMap(
      new java.util.concurrent.ConcurrentHashMap[T, java.lang.Boolean]).asScala
  }
}

