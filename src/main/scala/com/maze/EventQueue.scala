package com.maze

import java.util.Comparator
import java.util.concurrent.PriorityBlockingQueue

object EventOrderComparator extends Comparator[Event] {
  override def compare(o1: Event, o2: Event): Int = {
    Integer.compare(o1.sequenceNumber, o2.sequenceNumber)
  }
}

class EventQueue {
  val queue: PriorityBlockingQueue[Event] = new PriorityBlockingQueue[Event](64, EventOrderComparator)

  def enqueue(event: Event): Unit = {
    queue.put(event)
  }

  def nonEmpty(): Boolean= {
    queue.peek != null
  }

  def dequeue(): Event = {
    queue.take
  }

  def peek(): Event = {
    queue.peek
  }

  def eventOrder(e: Event) = -e.sequenceNumber
}
