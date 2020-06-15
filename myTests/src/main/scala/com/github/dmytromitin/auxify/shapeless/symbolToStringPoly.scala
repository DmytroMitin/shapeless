package com.github.dmytromitin.auxify.shapeless

import shapeless.{Poly1, Witness}

import scala.language.experimental.macros

object symbolToStringPoly extends Poly1 {
  implicit def cse1[S <: Symbol, S1 <: String](implicit
                                               sts: SymbolToString.Aux[S, S1],
                                               witness: Witness.Aux[S1]
                                              ): Case.Aux[S, S1] =
    at(_ => witness.value)
}

object stringToSymbolPoly extends Poly1 {
  implicit def cse[S <: String, S1 <: Symbol](implicit sts: StringToSymbol.Aux[S, S1], /*witness1: Witness.Aux[S],*/ witness: Witness.Aux[S1]): Case.Aux[S, S1] =
  //    at(_ => witness.value)
//    at((s: S) => stringToSymbol(s))
    macro StringSymbolMacros.stringToSymbolPolyCseImpl[S, S1]
}