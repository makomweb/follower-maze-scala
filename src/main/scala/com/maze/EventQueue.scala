package com.maze

import scala.collection.mutable.PriorityQueue

class EventQueue {
  val queue: PriorityQueue[Event] = PriorityQueue.empty[Event](Ordering.by(eventOrder))

  def enqueue(event: Event): Unit = {
    queue += event
  }

  def nonEmpty(): Boolean= {
    queue.nonEmpty
  }

  def dequeue(): Event = {
    queue.dequeue()
  }

  def peek(): Event = {
    queue.head
  }

  def eventOrder(e: Event) = -e.sequenceNumber
}
