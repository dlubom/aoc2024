package aoc.d01

import munit.FunSuite

class Day01Suite extends FunSuite:
  private val sample =
    """|3 4
       |4 3
       |2 5
       |1 3
       |3 9
       |3 3""".stripMargin

  // --- Parsing error tests ------------------------------------------------
  test("parse empty input") {
    Day01.parse("") match
      case Right(data) =>
        assertEquals(data.left.size, 0)
        assertEquals(data.right.size, 0)
      case Left(error) => fail(s"Expected success for empty input, got: $error")
  }

  test("parse single line") {
    Day01.parse("42 99") match
      case Right(data) =>
        assertEquals(data.left, Vector(42L))
        assertEquals(data.right, Vector(99L))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse line with tabs") {
    Day01.parse("123\t\t456") match
      case Right(data) =>
        assertEquals(data.left, Vector(123L))
        assertEquals(data.right, Vector(456L))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse line with mixed whitespace") {
    Day01.parse("789 \t \t 321") match
      case Right(data) =>
        assertEquals(data.left, Vector(789L))
        assertEquals(data.right, Vector(321L))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse should fail for line with only one number") {
    Day01.parse("123") match
      case Right(_)    => fail("Expected parse error for incomplete line")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for line with non-numeric text") {
    Day01.parse("abc 123") match
      case Right(_)    => fail("Expected parse error for non-numeric input")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for line with three numbers") {
    Day01.parse("123 456 789") match
      case Right(_)    => fail("Expected parse error for too many numbers")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for negative numbers") {
    Day01.parse("-123 456") match
      case Right(_)    => fail("Expected parse error for negative numbers")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for floating point numbers") {
    Day01.parse("123.45 678") match
      case Right(_)    => fail("Expected parse error for floating point numbers")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse mixed valid and invalid lines") {
    val invalidInput =
      """|123 456
         |invalid line
         |789 012""".stripMargin

    Day01.parse(invalidInput) match
      case Right(_)    => fail("Expected parse error for mixed valid/invalid input")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail with trailing whitespace on line") {
    Day01.parse("123 456   \n789 012   ") match
      case Right(_)    => fail("Expected parse error for trailing whitespace")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should succeed without trailing whitespace") {
    Day01.parse("123 456\n789 012") match
      case Right(data) =>
        assertEquals(data.left, Vector(123L, 789L))
        assertEquals(data.right, Vector(456L, 12L))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse should fail for extra spaces between numbers") {
    Day01.parse("123    456") match
      case Right(data) =>
        assertEquals(data.left, Vector(123L))
        assertEquals(data.right, Vector(456L))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse should fail for numbers with leading zeros") {
    Day01.parse("0123 0456") match
      case Right(data) =>
        assertEquals(data.left, Vector(123L))
        assertEquals(data.right, Vector(456L))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse should handle very long lines") {
    val longInput = (1 to 100).map(i => s"$i ${i * 2}").mkString("\n")
    Day01.parse(longInput) match
      case Right(data) =>
        assertEquals(data.left.size, 100)
        assertEquals(data.right.size, 100)
        assertEquals(data.left.head, 1L)
        assertEquals(data.right.head, 2L)
        assertEquals(data.left.last, 100L)
        assertEquals(data.right.last, 200L)
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse should fail for line ending with space only") {
    Day01.parse("123 ") match
      case Right(_)    => fail("Expected parse error for incomplete line ending with space")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for completely empty line in middle") {
    val inputWithEmptyLine =
      """|123 456
         |
         |789 012""".stripMargin

    Day01.parse(inputWithEmptyLine) match
      case Right(_)    => fail("Expected parse error for empty line in middle")
      case Left(error) => assert(error.contains("Parse error"))
  }

  // --- Sample tests -------------------------------------------------------
  test("part1 sample distance = 11") {
    Day01.parse(sample) match
      case Right(data) => assertEquals(Day01.part1(data), "11")
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("part2 sample similarity = 31") {
    Day01.parse(sample) match
      case Right(data) => assertEquals(Day01.part2(data), "31")
      case Left(error) => fail(s"Parse failed: $error")
  }

  // --- Regression tests on the full input ---------------------------------
  test("part1 actual answer") {
    val input = scala.io.Source.fromResource("inputs/day01.txt").mkString
    Day01.parse(input) match
      case Right(data) => assertEquals(Day01.part1(data), "1873376")
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("part2 actual answer") {
    val input = scala.io.Source.fromResource("inputs/day01.txt").mkString
    Day01.parse(input) match
      case Right(data) => assertEquals(Day01.part2(data), "18997088")
      case Left(error) => fail(s"Parse failed: $error")
  }
