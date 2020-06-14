package com.github.dmytromitin.auxify.shapeless

import shapeless.{Poly1, Witness}

object symbolToStringPoly extends Poly1 {
  implicit def cse1[S <: Symbol, S1 <: String](implicit
                                               sts: SymbolToString.Aux[S, S1],
                                               witness: Witness.Aux[S1]
                                              ): Case.Aux[S, S1] =
    at(_ => witness.value)
}