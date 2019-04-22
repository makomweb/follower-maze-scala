package com.maze

case class PrivateMessageEvent(override val sequenceNumber: Int, fromId: Int, toId: Int) extends Event(sequenceNumber) {
  override def toString = s"$sequenceNumber|P|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    val to = userRepository.get(toId)
    to.consume(this)
  }
}
