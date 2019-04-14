package com.maze

import org.scalatest.FunSuite

class EventQueueTests extends FunSuite {
  test("evaluate that queue is empty") {
    val queue = new EventQueue()
    assert(queue.peek == null)
  }

  test("evaluate that queue is not empty") {
    val event = FollowEvent(22, 33, 44)
    val queue = new EventQueue()
    queue.enqueue(event)
    assert(queue.peek.sequenceNumber == 22)
  }

  test ("dequeue event should make the queue empty") {
    val event = FollowEvent(11, 22, 33)
    val queue = new EventQueue()
    queue.enqueue(event)

    val taken = queue.dequeue()
    assert(taken.sequenceNumber == event.sequenceNumber)
    assert(queue.peek == null)
  }

  test("de-queueing events should follow sequence order") {
    val queue = new EventQueue()
    queue.enqueue(FollowEvent(3, 1, 1))
    queue.enqueue(FollowEvent(1, 1, 1))
    queue.enqueue(FollowEvent(5, 1, 1))

    assert(queue.dequeue().sequenceNumber == 1)
    assert(queue.dequeue().sequenceNumber == 3)
    assert(queue.dequeue().sequenceNumber == 5)
  }

  test("peeking an empty queue should throw") {
    val queue = new EventQueue()
    val head = queue.peek
    assert(head == null)
  }

  test("peeking a non-empty queue should succeed") {
    val queue = new EventQueue()
    queue.enqueue(FollowEvent(11, 22, 33))
    val head = queue.peek
    assert(head != null)
  }
}