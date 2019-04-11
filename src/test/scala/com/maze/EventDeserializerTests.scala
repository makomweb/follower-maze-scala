package com.maze

import org.scalatest.FunSuite

class EventDeserializerTests extends FunSuite {
  test("Instanciate event") {
    val event = new FollowEvent(22, 33, 44)
    assert(event.sequenceNumber == 22)
  }

  test("Deserialize event from raw string") {
    val event = EventDeserializer.deserialize("F|22|44|55")
    assert(event.sequenceNumber == 22)
  }
}
