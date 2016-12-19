package fr.kwizzy.webserver

import java.net.InetSocketAddress
import java.nio.channels.AsynchronousServerSocketChannel
import java.util.Scanner
import util.control.Breaks._

/**
  * Par Alexis le 19/12/2016.
  */
object Scapache {

  def main(arg : Array[String]) : Unit =
  {
    val server  = AsynchronousServerSocketChannel.open()
    server.bind(new InetSocketAddress(3000))
    server.accept(server, new WebServer())

    val scanner = new Scanner(System.in)
    breakable {
      while (true) {
        scanner.nextLine().toLowerCase() match {
          case e: String =>
            if (e.equalsIgnoreCase("exit")) {
              server.close()
            }
            else {
              println("Unknown command")
              break
            }
        }
      }
    }
  }
}
