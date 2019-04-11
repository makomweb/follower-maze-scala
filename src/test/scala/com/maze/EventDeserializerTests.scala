package com.maze

import org.scalatest.FunSuite

class EventDeserializerTests extends FunSuite {
  test("Event object should provide sequence number") {
    val event = new FollowEvent(22, 33, 44)
    assert(event.sequenceNumber == 22)
  }

  test("Deserialize event should private correct sequence number") {
    val event = EventDeserializer.deserialize("F|22|44|55")
    assert(event.sequenceNumber == 22)
  }

  test("Deserialize event should be of type FollowEvent") {
    val event = EventDeserializer.deserialize("F|22|44|55")
    assert(event.isInstanceOf[FollowEvent])
  }

  test("Deserialize event should be of type UnFollowEvent") {
    val event = EventDeserializer.deserialize("U|22|44|55")
    assert(event.isInstanceOf[UnFollowEvent])
  }

  test("Deserialize event should be of type PrivateMessageEvent") {
    val event = EventDeserializer.deserialize("P|22|44|55")
    assert(event.isInstanceOf[PrivateMessageEvent])
  }

  test("Deserialize event should be of type StatusUpdateEvent") {
    val event = EventDeserializer.deserialize("S|22|44")
    assert(event.isInstanceOf[StatusUpdateEvent])
  }

  test("Deserialize event should be of type BroadcastEvent") {
    val event = EventDeserializer.deserialize("B|22")
    assert(event.isInstanceOf[BroadcastEvent])
  }
}
