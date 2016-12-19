package fr.kwizzy.webserver

import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousServerSocketChannel, AsynchronousSocketChannel, CompletionHandler}

/**
  * Par Alexis le 19/12/2016.
  */
class WebServer extends CompletionHandler[AsynchronousSocketChannel, AsynchronousServerSocketChannel]{

  override def completed(client: AsynchronousSocketChannel, server: AsynchronousServerSocketChannel): Unit =
  {
    server.accept(server, this)
    val buffer = ByteBuffer.allocate(10240)
    client.read(buffer, buffer, new Client(client))
  }

  override def failed(exc: Throwable, attachment: AsynchronousServerSocketChannel): Unit =
  {
    exc.printStackTrace()
  }
}
