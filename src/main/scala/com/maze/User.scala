package com.maze

import java.io.PrintStream

import scala.collection.mutable.HashSet

class User(val id: Int, out: PrintStream) {
  val followerIds = HashSet[Int]()

  def addFollower(id: Int): Unit = {
    followerIds.add(id)
  }

  def removeFollower(id: Int): Unit = {
    followerIds.remove(id)
  }

  def consume(event: Event): Unit = {
    out.println(event)
    println(s"consumed event $event")
  }

  def notifyFollowers(event: StatusUpdateEvent, userRepository: UserRepository): Unit = {
    followerIds.foreach(followerId => {
      val follower = userRepository.get(followerId)
      follower.consume(event)
    })
  }
}
