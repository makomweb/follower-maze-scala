package com.maze

import scala.collection.mutable.ListBuffer

object EventQueue {
  val queue: ListBuffer[Event] = ListBuffer.empty[Event]

  def enqueue(event: Event): Unit = {
    queue += event
  }

  def nonEmpty(): Boolean= {
    return queue.nonEmpty
  }

  def dequeue(): Event = {
    val first = queue.take(1)
    return first.last
  }
}
