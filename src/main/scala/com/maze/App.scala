package com.maze

import java.net.ServerSocket
import java.util.concurrent.{ExecutorService, Executors}

object App {
  def main(args: Array[String]): Unit = {
    val threadPool: ExecutorService = Executors.newCachedThreadPool()
    val eventQueue: EventQueue = new EventQueue()
    val userRepository = new UserRepository

    val userClientSocket = new ServerSocket(9099)
    userClientSocket.setSoTimeout(1000)

    val incomingEventSocket = new ServerSocket(9090)
    incomingEventSocket.setSoTimeout(1000)

    val incomingEventSocketServer = new IncomingEventSocketServer(incomingEventSocket, threadPool, eventQueue)
    val userClientSocketServer = new UserClientSocketServer(userClientSocket, threadPool, userRepository)
    val eventQueueProcessor = new EventQueueProcessor(userRepository, eventQueue)

    threadPool.submit(incomingEventSocketServer)
    threadPool.submit(userClientSocketServer)
    threadPool.submit(eventQueueProcessor)
  }
}