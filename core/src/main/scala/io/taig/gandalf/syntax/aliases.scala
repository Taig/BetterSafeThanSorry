package io.taig.gandalf.syntax

import io.taig.gandalf.{ Apply, EagerAnd, LazyAnd, Mutation, Rule, Validation }

trait aliases {
    type ~>[L <: Mutation, R <: Validation.In[L#Output]] = Apply[L, R]

    type &[L <: Rule, R <: Rule.Aux[L#Input]] = EagerAnd[L, R]

    type &&[L <: Rule, R <: Rule.Aux[L#Input]] = LazyAnd[L, R]
}

object aliases extends aliases