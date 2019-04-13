package com.maze

abstract class Event(seqNo: Long) {
  def sequenceNumber = seqNo
  def raiseEvent(userRepository: UserRepository)
}

case class FollowEvent(seqNo: Long, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
  override def toString = s"$sequenceNumber|F|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.follow(fromId, toId, this)
  }
}

case class UnFollowEvent(seqNo: Long, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
  override def toString = s"$sequenceNumber|U|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    userRepository.unfollow(fromId, toId)
  }
}

case class PrivateMessageEvent(seqNo: Long, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
  override def toString = s"$sequenceNumber|P|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    val to = userRepository.get(toId)
    to.consume(this)
  }
}

case class StatusUpdateEvent(seqNo: Long, from: Int) extends Event(seqNo) {
  def fromId = from
  override def toString = s"$sequenceNumber|S|$fromId"

  override def raiseEvent(userRepository: UserRepository): Unit = {
    try {
      val from = userRepository.get(fromId)
      from.notifyFollowers(this, userRepository)
    } catch {
      case ex: RuntimeException => {
        println("notifyFollowers() has thrown!")
        println(ex)
      }
    }
  }
}

case class BroadcastEvent(seqNo: Long) extends Event(seqNo) {
  override def toString = s"$sequenceNumber|B"

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
      case "F" => FollowEvent(parts(0).toLong, parts(2).toInt, parts(3).toInt)
      case "U" => UnFollowEvent(parts(0).toLong, parts(2).toInt, parts(3).toInt)
      case "P" => PrivateMessageEvent(parts(0).toLong, parts(2).toInt, parts(3).toInt)
      case "S" => StatusUpdateEvent(parts(0).toLong, parts(2).toInt)
      case "B" => BroadcastEvent(parts(0).toLong)
    }
  }
}
