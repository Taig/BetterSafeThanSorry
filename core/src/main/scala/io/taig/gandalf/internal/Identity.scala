package io.taig.gandalf.internal

import io.taig.gandalf.Transformation

final class Identity[T] extends Transformation {
    override type Input = T

    override type Output = T
}

object Identity {
    def apply[T]: Identity[T] = new Identity[T]
}