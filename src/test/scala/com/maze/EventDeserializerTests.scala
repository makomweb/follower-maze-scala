package com.maze

import org.scalatest.FunSuite

class EventDeserializerTests extends FunSuite {
  test("Event object should provide sequence number") {
    val event = FollowEvent(22, 33, 44)
    assert(event.sequenceNumber == 22)
  }

  test("Deserialize event should private correct sequence number") {
    val event = EventDeserializer.deserialize("22|F|44|55")
    assert(event.sequenceNumber == 22)
  }

  test("Deserialize event should be of type FollowEvent") {
    val event = EventDeserializer.deserialize("22|F|44|55")
    assert(event.isInstanceOf[FollowEvent])
  }

  test("Deserialize event should be of type UnFollowEvent") {
    val event = EventDeserializer.deserialize("22|U|44|55")
    assert(event.isInstanceOf[UnFollowEvent])
  }

  test("Deserialize event should be of type PrivateMessageEvent") {
    val event = EventDeserializer.deserialize("22|P|44|55")
    assert(event.isInstanceOf[PrivateMessageEvent])
  }

  test("Deserialize event should be of type StatusUpdateEvent") {
    val event = EventDeserializer.deserialize("22|S|44")
    assert(event.isInstanceOf[StatusUpdateEvent])
  }

  test("Deserialize event should be of type BroadcastEvent") {
    val event = EventDeserializer.deserialize("22|B")
    assert(event.isInstanceOf[BroadcastEvent])
  }
}
