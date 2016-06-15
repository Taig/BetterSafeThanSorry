package io.taig.gandalf.data

trait Action {
    type Input

    type Output
}

object Action {
    type Input[I] = Action { type Input = I }

    type Output[O] = Action { type Output = O }

    type Aux[I, O] = Action { type Input = I; type Output = O }
}