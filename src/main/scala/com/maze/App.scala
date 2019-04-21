package com.maze

import java.net.ServerSocket
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

object App {
  def main(args: Array[String]): Unit = {
    val threadPool = Executors.newCachedThreadPool()
    val eventQueue = new EventQueue
    val userRepository = new UserRepository
    val wasCancelled : AtomicBoolean = new AtomicBoolean(false)

    val userClientSocket = new ServerSocket(9099)
    userClientSocket.setSoTimeout(1000)

    val incomingEventSocket = new ServerSocket(9090)
    incomingEventSocket.setSoTimeout(1000)

    val incomingEventSocketServer = new IncomingEventSocketServer(incomingEventSocket, threadPool, eventQueue, wasCancelled)
    val userClientSocketServer = new UserClientSocketServer(userClientSocket, threadPool, userRepository, wasCancelled)
    val eventQueueProcessor = new EventQueueProcessor(userRepository, eventQueue, wasCancelled)

    threadPool.submit(incomingEventSocketServer)
    threadPool.submit(userClientSocketServer)
    threadPool.submit(eventQueueProcessor)
  }
}