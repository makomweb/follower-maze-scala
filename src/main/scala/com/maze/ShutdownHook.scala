package com.maze

import java.util.concurrent.atomic.AtomicBoolean

class ShutdownHook(wasCancelled: AtomicBoolean, mainThread: Thread) extends Thread {
  override def run = {
    wasCancelled.set(true)
    mainThread.join()
  }
}
