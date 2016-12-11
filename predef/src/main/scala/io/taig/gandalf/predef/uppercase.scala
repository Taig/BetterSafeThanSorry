package io.taig.gandalf.predef

import io.taig.gandalf._

class uppercase extends Rule.Transition

object uppercase extends uppercase {
    implicit val string: Validation[uppercase, String, String] =
        Validation.transition( _.toUpperCase )
}