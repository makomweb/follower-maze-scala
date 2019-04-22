package com.maze

case class PrivateMessageEvent(seqNo: Int, fromId: Int, toId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|P|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    val to = userRepository.get(toId)
    to.consume(this)
  }
}
