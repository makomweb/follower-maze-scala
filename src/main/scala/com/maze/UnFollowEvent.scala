package com.maze

case class UnFollowEvent(seqNo: Int, fromId: Int, toId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|U|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.unfollow(fromId, toId)
  }
}
