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

case class BroadcastEvent(seqNo: Int) extends Event(seqNo) {}

case class PrivateMessageEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
}

case class StatusUpdateEvent(seqNo: Int, from: Int, to: Int) extends Event(seqNo) {
  def fromId = from
  def toId = to
}

object EventDeserializer {
  def deserialize(raw: String): Event = {
    return new FollowEvent(1,2,3)
  }

  def deserialized(parts: Array[String]): Event = {

  }
}
