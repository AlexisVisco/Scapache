package fr.kwizzy.webserver

import scala.collection.mutable

/**
  * Par Alexis le 19/12/2016.
  */
case class Request(method: String, requestURI: String,
                   httpVersion: String, headers: mutable.HashMap[String, String],
                   data: String)
{

}
