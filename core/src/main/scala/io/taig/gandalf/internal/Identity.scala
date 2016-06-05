package io.taig.gandalf.internal

import io.taig.gandalf.Transformation

class Identity[T] extends Transformation {
    override type Input = T

    override type Output = T

    override def transform( input: T ) = input
}

object Identity {
    def apply[T]: Identity[T] = new Identity[T]
}