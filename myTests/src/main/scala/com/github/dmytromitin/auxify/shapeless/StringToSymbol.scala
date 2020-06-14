package com.github.dmytromitin.auxify.shapeless

import com.github.dmytromitin.auxify.macros.{apply, aux, instance}
import shapeless.Witness
import shapeless.tag.@@

@aux @apply @instance
trait StringToSymbol[S <: String] {
  type Out <: Symbol
}
object StringToSymbol {
  implicit def mkStringToSymbol[S <: String](implicit witness: Witness.Aux[S]): StringToSymbol.Aux[S, Symbol @@ S] = null
}

@aux @apply @instance
trait SymbolToString[S <: Symbol] {
  type Out <: String
}
object SymbolToString {
  implicit def mkSymbolToString[S <: String](implicit witness: Witness.Aux[S]): SymbolToString.Aux[Symbol @@ S, S] = null
}