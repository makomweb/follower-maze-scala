package com.maze

case class UnFollowEvent(override val sequenceNumber: Int, fromId: Int, toId: Int) extends Event(sequenceNumber) {
  override def toString = s"$sequenceNumber|U|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.unfollow(fromId, toId)
  }
}
