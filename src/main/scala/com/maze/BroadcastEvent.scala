package com.maze

case class BroadcastEvent(seqNo: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|B"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.allUsers().foreach(user => {
      user.consume(this)
    })
  }
}
