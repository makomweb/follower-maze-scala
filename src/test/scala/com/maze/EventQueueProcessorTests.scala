package com.maze

import org.scalatest.FunSuite

class EventQueueProcessorTests extends FunSuite {
  test("test event queue processor") {
    val userRepository = new UserRepository
    val eventQueue = new EventQueue

    userRepository.addDummy(1)
    userRepository.addDummy(2)

    eventQueue.enqueue(PrivateMessageEvent(10, 1, 2))
    eventQueue.enqueue(PrivateMessageEvent(11, 2, 3))
    eventQueue.enqueue(PrivateMessageEvent(12, 2, 1))

    eventQueue.enqueue(FollowEvent(1, 1, 2))
    eventQueue.enqueue(FollowEvent(2, 2, 3))
    eventQueue.enqueue(FollowEvent(3, 2, 1))

    eventQueue.enqueue(BroadcastEvent(4))
    eventQueue.enqueue(BroadcastEvent(5))
    eventQueue.enqueue(BroadcastEvent(6))

    eventQueue.enqueue(StatusUpdateEvent(7, 1))
    eventQueue.enqueue(StatusUpdateEvent(8, 2))
    eventQueue.enqueue(StatusUpdateEvent(9, 3))

    //eventQueue.enqueue(UnFollowEvent(4, 1, 2))
    //eventQueue.enqueue(UnFollowEvent(5, 2, 3))
    //eventQueue.enqueue(UnFollowEvent(6, 2, 1))

    val eventQueueProcessor = new EventQueueProcessor(userRepository, eventQueue)
    eventQueueProcessor.run()
  }
}
