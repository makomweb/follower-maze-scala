package com.maze

case class FollowEvent(override val sequenceNumber: Int, fromId: Int, toId: Int) extends Event(sequenceNumber) {
  override def toString = s"$sequenceNumber|F|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.follow(fromId, toId, this)
  }
}
