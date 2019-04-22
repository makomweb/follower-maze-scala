package com.maze

case class StatusUpdateEvent(override val sequenceNumber: Int, fromId: Int) extends Event(sequenceNumber) {
  override def toString = s"$sequenceNumber|S|$fromId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    try {
      val from = userRepository.get(fromId)
      from.notifyFollowers(this, userRepository)
    } catch {
      case ex: Throwable => {
        Logger.logExceptionNotifyFollowers(ex)
      }
    }
  }
}
