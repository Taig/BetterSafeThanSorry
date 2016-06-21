package io.taig.gandalf

case class LazyAnd[L <: Rule, R <: Rule.Aux[L#Input]](
    left:  Evaluation[L],
    right: Evaluation[R]
) extends Operation[L, R]

object LazyAnd {
    implicit def evaluation[L <: Rule, R <: Rule.Aux[L#Output]](
        implicit
        lev: Evaluation[L],
        ler: Error[L],
        rev: Evaluation[R],
        rer: Error[R],
        e:   Error[L LazyAnd R]
    ) = {
        Evaluation.instance[L LazyAnd R] { input ⇒
            ( lev.validate( input ) andThen rev.validate )
                .leftMap( e.error.map( List( _ ) ).getOrElse( _ ) )
        }
    }
}