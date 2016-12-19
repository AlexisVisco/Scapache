package fr.kwizzy.util

import scala.annotation.tailrec

/**
  * Par Alexis le 19/12/2016.
  */
class UtilArray {

  implicit class zip[A](s: Array[A])  {

      def foreachIndexReturn(startIndex: Int, func: (A, A) => A): A = {
        var v : A = null.asInstanceOf[A]
        for (i <- startIndex until s.length)
        {
          v = func.apply(s(i), v)
        }
        v
      }
  }

}
