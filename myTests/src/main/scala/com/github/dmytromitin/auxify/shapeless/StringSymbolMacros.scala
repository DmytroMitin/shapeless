package com.github.dmytromitin.auxify.shapeless

import macrocompat.bundle
import shapeless.SingletonTypeUtils

import scala.reflect.macros.whitebox

@bundle
class StringSymbolMacros(val c: whitebox.Context) extends SingletonTypeUtils {
  import c.universe._

  def stringToSymbolImpl(s: Tree): Tree = {
    q"""
      import _root_.shapeless.syntax.singleton._
      _root_.com.github.dmytromitin.auxify.shapeless.`package`.stringToSymbolHlp($s.narrow)
    """
  }

  def stringToSymbolPolyCseImpl[S <: String: WeakTypeTag, S1 <: scala.Symbol: WeakTypeTag](sts: Tree, witness: Tree): Tree = {
    //    at((s: S) => stringToSymbol(s))
    val s = c.freshName("s")
    q"""
       _root_.com.github.dmytromitin.auxify.shapeless.stringToSymbolPoly.at[${weakTypeOf[S]}].apply[${weakTypeOf[S1]}](
         ($s: ${weakTypeOf[S]}) => _root_.com.github.dmytromitin.auxify.shapeless.`package`.stringToSymbol($s)
       )
       """
  }
}