package com.maze

import java.io.ByteArrayOutputStream
import java.util.concurrent.atomic.AtomicBoolean

import org.scalatest.FunSuite

class EventQueueProcessorTests extends FunSuite {
  test("test event queue processor") {
    val byteStream = new ByteArrayOutputStream
    val stream = PrintWriterCreator.fromStream(byteStream)

    val userRepository = new UserRepository
    val eventQueue = new EventQueue

    userRepository.add(1, stream, false)
    userRepository.add(2, stream, false)
    userRepository.add(3, stream, false)


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

    val eventQueueProcessor = new EventQueueProcessor(userRepository, eventQueue, new AtomicBoolean(false))
    eventQueueProcessor.processQueue

    val bytes = byteStream.toByteArray
    val str = new String(bytes)

    val expected = "1|F|1|2\n" +
      "2|F|2|3\n" +
      "3|F|2|1\n" +
      "4|B\n" +
      "4|B\n" +
      "4|B\n" +
      "5|B\n" +
      "5|B\n" +
      "5|B\n" +
      "6|B\n" +
      "6|B\n" +
      "6|B\n" +
      "7|S|1\n" +
      "8|S|2\n" +
      "9|S|3\n" +
      "10|P|1|2\n" +
      "11|P|2|3\n" +
      "12|P|2|1\n"

    //println(str)

    assert(expected == str)
  }
}
