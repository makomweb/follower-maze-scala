package com.maze

abstract class Event(seqNo: Int) {
  def sequenceNumber = seqNo
  def raiseEvent(userRepository: UserRepository)
}

case class FollowEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
  override def toString = s"$sequenceNumber|F|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = ???
}

case class UnFollowEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
  override def toString = s"$sequenceNumber|U|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = ???
}

case class PrivateMessageEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
  override def toString = s"$sequenceNumber|P|$fromId|$toId"

  override def raiseEvent(userRepository: UserRepository): Unit = ???
}

case class StatusUpdateEvent(seqNo: Int, from: Int) extends Event(seqNo) {
  def fromId = from
  override def toString = s"$sequenceNumber|S|$fromId"

  override def raiseEvent(userRepository: UserRepository): Unit = ???
}

case class BroadcastEvent(seqNo: Int) extends Event(seqNo) {
  override def toString = s"$sequenceNumber|B"

  override def raiseEvent(userRepository: UserRepository): Unit = ???
}

object EventDeserializer {
  def deserialize(raw: String): Event = {
    val parts = raw.split("\\|")
    deserialize(parts)
  }

  def deserialize(parts: Array[String]): Event = {
    parts(0) match {
      case "F" => FollowEvent(parts(1).toInt, parts(2).toInt, parts(3).toInt)
      case "U" => UnFollowEvent(parts(1).toInt, parts(2).toInt, parts(3).toInt)
      case "P" => PrivateMessageEvent(parts(1).toInt, parts(2).toInt, parts(3).toInt)
      case "S" => StatusUpdateEvent(parts(1).toInt, parts(2).toInt)
      case "B" => BroadcastEvent(parts(1).toInt)
    }
  }
}
