package shapeless

import com.github.dmytromitin.auxify.shapeless.{stringToSymbol, symbolToStringPoly}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import shapeless.syntax.singleton._
import shapeless.tag.{@@, Tagged}
import shapeless.test.{illTyped, sameTyped}

class WitnessTest extends AnyFlatSpec with Matchers {
  type Str = Witness.`"a"`.T
  type Smb = Symbol
  type Tgd[X] = Tagged[X]
  implicitly[Witness.Aux[Symbol @@ Str]]
  implicitly[Witness.Aux[Symbol with Tagged[Str]]]
  implicitly[Witness.Aux[Smb with Tgd[Str]]]

  type Symb = Witness.`'a`.T
  sameTyped[Symb](stringToSymbol("a"))(Symbol("a").narrow)

  implicitly[Witness.Aux[Symb]]

  implicitly[poly.Case[symbolToStringPoly.type, (Symbol @@ Witness.`"a"`.T) :: HNil]]
  sameTyped[Witness.`"a"`.T](symbolToStringPoly.apply[Witness.`'a`.T]('a.narrow))("a".narrow)

  illTyped("implicitly[Witness.Aux[Int]]")

  trait B
  case object A extends B

  trait B1 {
    type T <: B
    def getT(implicit w: Witness.Aux[T]): T = w.value
  }
  case class A1() extends B1 {
    type T = A.type
  }

  sameTyped[A.type](A1().getT)(A)

  "Witness" should "work" in {
    A1().getT should be(A)
  }
}