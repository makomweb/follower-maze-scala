package com.maze

case class BroadcastEvent(override val sequenceNumber: Int) extends Event(sequenceNumber) {
  override def toString = s"$sequenceNumber|B"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.allUsers().foreach(user => {
      user.consume(this)
      Logger.logEventConsumed(user, this)
    })
  }
}
