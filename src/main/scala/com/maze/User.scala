package com.maze

import java.io.PrintWriter

class User(val id: Int, out: PrintWriter, dummy: Boolean) {
  val followerIds = createSet[Int]

  def addFollower(id: Int): Unit = {
    followerIds.add(id)
  }

  def removeFollower(id: Int): Unit = {
    followerIds.remove(id)
  }

  def consume(event: Event): Boolean = {
    if (!dummy) {
      out.print(s"$event\n")
      !out.checkError
    }

    println(s"Consumed event: $event")
    true
  }

  def notifyFollowers(event: StatusUpdateEvent, userRepository: UserRepository): Unit = {
    followerIds.foreach(followerId => {
      val follower = userRepository.get(followerId)
      val success = follower.consume(event)
      if (!success) {
        removeFollower(followerId)
      }
    })
  }

  def createSet[T]() = {
    import scala.collection.JavaConverters._
    java.util.Collections.newSetFromMap(
      new java.util.concurrent.ConcurrentHashMap[T, java.lang.Boolean]).asScala
  }
}

