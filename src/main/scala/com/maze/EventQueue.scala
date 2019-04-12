package com.maze

import scala.collection.mutable.PriorityQueue

class EventQueue {
  val queue: PriorityQueue[Event] = PriorityQueue.empty[Event](Ordering.by(eventOrder))

  def enqueue(event: Event): Unit = {
    queue += event
  }

  def nonEmpty(): Boolean= {
    return queue.nonEmpty
  }

  def dequeue(): Event = {
    return queue.dequeue()
  }

  def eventOrder(e: Event) = e.sequenceNumber
}
