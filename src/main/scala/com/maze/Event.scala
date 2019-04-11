package com.maze

abstract class Event(seqNo: Int) {
  def sequenceNumber = seqNo
}

case class FollowEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
}

case class UnFollowEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
}

case class PrivateMessageEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
}

case class StatusUpdateEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
}

case class BroadcastEvent(seqNo: Int) extends Event(seqNo) {}

object EventDeserializer {
  def deserialize(raw: String): Event = {
    val parts = raw.split("\\|")
    return deserialize(parts)
  }

  def deserialize(parts: Array[String]): Event = {
    val event = parts(0) match {
      case "F" => FollowEvent(parts(1).toInt, parts(2).toInt, parts(3).toInt)
    }
    return event
  }
}
