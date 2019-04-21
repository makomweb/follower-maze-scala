package com.maze

import java.util.Comparator
import java.util.concurrent.PriorityBlockingQueue

object EventOrderComparator extends Comparator[Event] {
  override def compare(one: Event, other: Event): Int = {
    Integer.compare(one.sequenceNumber, other.sequenceNumber)
  }
}

class EventQueue {
  val queue: PriorityBlockingQueue[Event] = new PriorityBlockingQueue[Event](64, EventOrderComparator)

  def enqueue(event: Event): Unit = {
    this.synchronized {
      queue.put(event)
    }
  }

  def dequeue(): Event = {
    this.synchronized {
      queue.take
    }
  }

  def peek(): Event = {
    this.synchronized {
      queue.peek
    }
  }
}
