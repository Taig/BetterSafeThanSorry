package io.taig.gandalf.tests

import cats.data.NonEmptyList
import cats.data.Validated._
import io.taig.gandalf.implicits._
import io.taig.gandalf.predef.IsDefined
import io.taig.gandalf.predef.Matches
import io.taig.gandalf.predef.Required
import io.taig.gandalf.predef.Trim

class SyntaxTest extends Suite {
    "<~>" should "allow to transform data" in {
        ( Trim <~> Required ).validate( "foo" ) shouldBe valid( "foo" )
        ( Trim <~> Required ).validate( " foo  " ) shouldBe valid( "foo" )
        ( Trim <~> Required ).validate( "" ) shouldBe invalidNel( "Required" )
        ( Trim <~> Required ).validate( "   " ) shouldBe invalidNel( "Required" )
    }

    it should "be aliased by ~>" in {
        ( Trim <~> Required ) shouldBe ( Trim ~> Required )
    }

    "<*>" should "allow to mutate data" in {
        ( IsDefined[String] <*> Required ).validate( Some( "foo" ) ) shouldBe valid( "foo" )
        ( IsDefined[String] <*> Required ).validate( Some( "" ) ) shouldBe invalidNel( "Required" )
        ( IsDefined[String] <*> Required ).validate( None ) shouldBe invalidNel( "IsDefined" )
    }

    it should "be aliased by ~>" in {
        ( IsDefined[String] <*> Required ) shouldBe ( IsDefined[String] ~> Required )
    }

    "&&" should "mimic the logical AND" in {
        ( Required && Matches( "foo" ) ).validate( "foo" ) shouldBe valid( "foo" )
        ( Required && Matches( "foo" ) ).validate( "foobar" ) shouldBe invalidNel( "Matches" )
        ( Required && Matches( "foo" ) ).validate( "" ) shouldBe invalidNel( "Required" )
    }

    "&" should "mimic the bitwise AND" in {
        ( Required & Matches( "foo" ) ).validate( "foo" ) shouldBe valid( "foo" )
        ( Required & Matches( "foo" ) ).validate( "foobar" ) shouldBe invalidNel( "Matches" )
        ( Required & Matches( "foo" ) ).validate( "" ) shouldBe invalid( NonEmptyList( "Required", "Matches" ) )
    }

    "||" should "mimic the logical OR" in {
        ( Required || Matches( "foo" ) ).validate( "foo" ) shouldBe valid( "foo" )
        ( Required || Matches( "foo" ) ).validate( "foobar" ) shouldBe valid( "foobar" )
        ( Required || Matches( "foo" ) ).validate( "" ) shouldBe invalid( NonEmptyList( "Required", "Matches" ) )
    }
}