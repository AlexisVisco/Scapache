package fr.kwizzy.webserver

import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousSocketChannel, CompletionHandler}

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.control.Breaks._


/**
  * Par Alexis le 19/12/2016.
  */
class Client(client: AsynchronousSocketChannel) extends CompletionHandler[Integer, ByteBuffer] {

  override def completed(result: Integer, buffer: ByteBuffer): Unit = {
    if (result == -1)
      return
    val read = Array.ofDim[Byte](result)
    buffer.flip()
    buffer.get(read)
    val req = Client.parseRequest(new String(read))
    val resp = Response(req.httpVersion, 200, "ok", new mutable.HashMap[String, String], "<h1> Hello world <h1>")
    resp.headers += ("content-type" -> "text/html")
    client.write(ByteBuffer.wrap(Client.serializedResponse(resp).getBytes()), client, CloseCompletionHandler)
  }

  override def failed(exc: Throwable, attachment: ByteBuffer): Unit = {
    exc.printStackTrace()
  }

}

object Client {
  val SP = " "
  val CRLF = "\r\n"

  def parseRequest(str: String): Request = {
    val lines: Array[String] = str.split(CRLF)
    val requestLine = lines(0).split(SP)
    var headers = new mutable.HashMap[String, String]
    var data = ""
    var fIndex = 1
    breakable {
      foreachWithIndex(fIndex, lines)((index, str) => {
        val header = str.split(":", 2)
        if (header.length != 2)
          break
        headers += (header(0) -> header(1).replaceFirst(" ", ""))
        fIndex = index
      })
    }
    foreachWithIndex(fIndex, lines)((_, str) => {
      data += str
    })
    Request(requestLine(0), requestLine(1), requestLine(2), headers, data)
  }

  def serializedResponse(resp: Response): String = {
    resp.httpVersion + SP + resp.statusCode + SP + resp.reasonMessage + CRLF +
    resp.headers.map(e => e._1 + ":" + e._2).mkString + CRLF + CRLF + resp.body + CRLF
  }

  @tailrec def foreachWithIndex(start: Int, xs: Array[String])(apply: (Int, String) => Unit) {
    if (start < xs.length) {
      apply.apply(start, xs(start))
      foreachWithIndex(start + 1, xs)(apply)
    }
  }
}
