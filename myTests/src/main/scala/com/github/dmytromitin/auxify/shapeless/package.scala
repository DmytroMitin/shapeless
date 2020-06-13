package com.github.dmytromitin.auxify

import _root_.shapeless.Witness

package object shapeless {

  import scala.language.experimental.macros

  def stringToSymbolHlp[S <: String, S1 <: Symbol](s: S)(implicit
                                                         sts: StringToSymbol.Aux[S, S1],
                                                         witness: Witness.Aux[S1]): S1 = witness.value

  def stringToSymbol(s: String): Symbol = macro StringSymbolMacros.stringToSymbolImpl
}
