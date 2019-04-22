package com.maze

abstract class Event(seqNo: Int) {
  def raiseEvent(userRepository: UserRepository)
  def sequenceNumber = seqNo
}