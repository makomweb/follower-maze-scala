package com.maze

import java.util.Comparator

object EventOrderComparator extends Comparator[Event] {
  override def compare(one: Event, other: Event): Int = {
    Integer.compare(one.sequenceNumber, other.sequenceNumber)
  }
}
