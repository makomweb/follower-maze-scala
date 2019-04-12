package com.maze

import java.io.PrintWriter

import scala.collection.mutable.HashSet

class User(val id: Int, writer: PrintWriter) {
  val followerIds = HashSet[Int]()

  def addFollower(id: Int): Unit = {
    followerIds.add(id)
  }

  def removeFollower(id: Int): Unit = {
    followerIds.remove(id)
  }

  def consume(event: Event): Boolean = {
    writer.print(event)
    writer.print("\r\n")
    writer.flush()
    !writer.checkError()
  }

  def notifyFollowers(event: StatusUpdateEvent, userRepository: UserRepository): Unit = {
    followerIds.foreach(followerId => {
      val follower = userRepository.get(followerId)
      val success = follower.consume(event)
      if (!success) {
        followerIds.remove(followerId)
      }
    })
  }
}
