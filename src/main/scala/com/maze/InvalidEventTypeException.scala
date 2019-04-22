package com.maze

case class InvalidEventTypeException(parts: Array[String]) extends Exception(s"Invalid event type: $parts")
