package com.maze

import java.util.concurrent.PriorityBlockingQueue

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
