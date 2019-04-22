package com.maze

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
      case _ => Logger.logUnknownEventType(parts)
    }
  }
}
