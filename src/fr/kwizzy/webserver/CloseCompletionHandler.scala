package fr.kwizzy.webserver

import java.nio.channels.{AsynchronousSocketChannel, CompletionHandler}

/**
  * Par Alexis le 19/12/2016.
  */
object CloseCompletionHandler extends CompletionHandler[Integer, AsynchronousSocketChannel] {

  override def completed(result: Integer, attachment: AsynchronousSocketChannel): Unit =
  {
    try {
      attachment.close()
    }
    catch {
      case e : Exception => failed(e, attachment)
    }
  }

  override def failed(exc: Throwable, attachment: AsynchronousSocketChannel): Unit =
  {
    exc.printStackTrace()
  }
}
