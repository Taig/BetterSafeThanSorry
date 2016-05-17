package io.taig.gandalf.typelevel.ops

import io.taig.gandalf.typelevel.{ EagerAnd, Evaluation, LazyAnd, Rule }

class rule[L <: Rule]( left: Evaluation[L] ) {
    def &&[R <: Rule.Aux[L#Output]]( right: Evaluation[R] ): L LazyAnd R = LazyAnd( left, right )

    def &[R <: Rule.Aux[L#Output]]( right: Evaluation[R] ): L EagerAnd R = EagerAnd( left, right )
}