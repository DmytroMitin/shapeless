package shapeless

import com.github.dmytromitin.auxify.shapeless.stringToSymbol
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import shapeless.syntax.singleton._
import shapeless.tag.{@@, Tagged}
import shapeless.test.sameTyped

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

  "Witness" should "work" in {
    true should be(true)
  }
}
