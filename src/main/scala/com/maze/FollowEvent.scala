package com.maze

case class FollowEvent(seqNo: Int, fromId: Int, toId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|F|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.follow(fromId, toId, this)
  }
}
