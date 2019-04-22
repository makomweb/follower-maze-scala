package com.maze

case class StatusUpdateEvent(seqNo: Int, fromId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|S|$fromId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    try {
      val from = userRepository.get(fromId)
      from.notifyFollowers(this, userRepository)
    } catch {
      case ex: RuntimeException => {
        println(s"notifyFollowers() has thrown: $ex")
      }
    }
  }
}
