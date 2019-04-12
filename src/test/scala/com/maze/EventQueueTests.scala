package com.maze

import org.scalatest.FunSuite

class EventQueueTests extends FunSuite {
  test("evaluate that queue is empty") {
    val queue = EventQueue
    assert(!queue.nonEmpty())
  }

  test("evaluate that queue is not empty") {
    val event = FollowEvent(22, 33, 44)
    val queue = EventQueue
    queue.enqueue(event)
    assert(queue.nonEmpty())
  }
}