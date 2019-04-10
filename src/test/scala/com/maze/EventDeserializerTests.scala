package com.maze

import org.scalatest.FunSuite

class EventDeserializerTests extends FunSuite {
  test("Deserialize event") {
    val event = new FollowEvent(22, 33, 44)
    assert(event.sequenceNumber == 22)
  }
}
