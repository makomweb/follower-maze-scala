package com.maze

abstract class Event(val sequenceNumber: Int) {
  def raiseEvent(userRepository: UserRepository)
}