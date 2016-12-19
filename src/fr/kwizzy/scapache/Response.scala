package fr.kwizzy.scapache

import java.util

import scala.collection.mutable
import scala.collection.mutable.HashMap


/**
  * Par Alexis le 19/12/2016.
  */
case class Response(httpVersion: String, statusCode: Int,
                    reasonMessage: String, headers: mutable.HashMap[String, String],
                    body: String)
{

}
