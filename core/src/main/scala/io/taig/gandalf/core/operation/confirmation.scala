package io.taig.gandalf.core.operation

import io.taig.gandalf.core.{ Rule, Validation }

final class confirmation[I]( val input: I ) extends AnyVal {
    def confirm[R <: Rule]( rule: R )(
        implicit
        v: Validation[R, I]
    ): Option[v.Out] = v.confirm( input )
}