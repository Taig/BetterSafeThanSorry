package io.taig.bsts

import shapeless.HList

trait Validation[I, O] {
    type V <: HList

    type R

    def validations: V

    def validate( input: I ): R
}

object Validation {
    type Aux[I, O, V0 <: HList, R0] = Validation[I, O] { type V = V0; type R = R0 }
}