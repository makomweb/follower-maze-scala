package com.maze

import org.scalatest.FunSuite

class EventQueueTests extends FunSuite {
  test("evaluate that queue is empty") {
    val queue = new EventQueue()
    assert(!queue.nonEmpty())
  }

  test("evaluate that queue is not empty") {
    val event = FollowEvent(22, 33, 44)
    val queue = new EventQueue()
    queue.enqueue(event)
    assert(queue.nonEmpty())
  }

  test ("dequeue event should make the queue empty") {
    val event = FollowEvent(11, 22, 33)
    val queue = new EventQueue()
    queue.enqueue(event)

    val taken = queue.dequeue()
    assert(taken.sequenceNumber == event.sequenceNumber)
    assert(!queue.nonEmpty())
  }
}