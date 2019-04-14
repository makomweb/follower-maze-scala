package com.maze

abstract class Event(seqNo: Int) {
  def raiseEvent(userRepository: UserRepository)
  def sequenceNumber = seqNo
}

case class FollowEvent(seqNo: Int, fromId: Int, toId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|F|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.follow(fromId, toId, this)
  }
}

case class UnFollowEvent(seqNo: Int, fromId: Int, toId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|U|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.unfollow(fromId, toId)
  }
}

case class PrivateMessageEvent(seqNo: Int, fromId: Int, toId: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|P|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    val to = userRepository.get(toId)
    to.consume(this)
  }
}

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

case class BroadcastEvent(seqNo: Int) extends Event(seqNo) {
  override def toString = s"$seqNo|B"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.allUsers().foreach(user => {
      user.consume(this)
    })
  }
}

object EventDeserializer {
  def deserialize(raw: String): Event = {
    val parts = raw.split("\\|")
    deserialize(parts)
  }

  def deserialize(parts: Array[String]): Event = {
    parts(1) match {
      case "F" => FollowEvent(parts(0).toInt, parts(2).toInt, parts(3).toInt)
      case "U" => UnFollowEvent(parts(0).toInt, parts(2).toInt, parts(3).toInt)
      case "P" => PrivateMessageEvent(parts(0).toInt, parts(2).toInt, parts(3).toInt)
      case "S" => StatusUpdateEvent(parts(0).toInt, parts(2).toInt)
      case "B" => BroadcastEvent(parts(0).toInt)
    }
  }
}
