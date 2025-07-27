package aoc.d02

import munit.FunSuite

class Day02Suite extends FunSuite:
  private val sample =
    """|7 6 4 2 1
       |1 2 7 8 9
       |9 7 6 2 1
       |1 3 2 4 5
       |8 6 4 4 1
       |1 3 6 7 9""".stripMargin

  // --- Parsing error tests ------------------------------------------------
  test("parse empty input") {
    Day02.parse("") match
      case Right(data) =>
        assertEquals(data.size, 0)
      case Left(error) => fail(s"Expected success for empty input, got: $error")
  }

  test("parse single line") {
    Day02.parse("42 99") match
      case Right(data) =>
        assertEquals(data, List(List(42, 99)))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse line with tabs") {
    Day02.parse("123\t\t456") match
      case Right(data) => assertEquals(data, List(List(123, 456)))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse line with mixed whitespace") {
    Day02.parse("789 \t \t 321") match
      case Right(data) => assertEquals(data, List(List(789, 321)))
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("parse should fail for line with non-numeric text") {
    Day02.parse("abc 123") match
      case Right(_)    => fail("Expected parse error for non-numeric input")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for negative numbers") {
    Day02.parse("-123 456") match
      case Right(_)    => fail("Expected parse error for negative numbers")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse should fail for floating point numbers") {
    Day02.parse("123.45 678") match
      case Right(_)    => fail("Expected parse error for floating point numbers")
      case Left(error) => assert(error.contains("Parse error"))
  }

  test("parse mixed valid and invalid lines") {
    val invalidInput =
      """|123 456
         |invalid line
         |789 012""".stripMargin

    Day02.parse(invalidInput) match
      case Right(_)    => fail("Expected parse error for mixed valid/invalid input")
      case Left(error) => assert(error.contains("Parse error"))
  }

  // --- Sample tests -------------------------------------------------------
  test("part1 sample safe count = 2") {
    Day02.parse(sample) match
      case Right(data) => assertEquals(Day02.part1(data), "2")
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("part2 sample similarity = 4") {
    Day02.parse(sample) match
      case Right(data) => assertEquals(Day02.part2(data), "4")
      case Left(error) => fail(s"Parse failed: $error")
  }

  // --- Regression tests on the full input ---------------------------------
  test("part1 actual answer") {
    val input = scala.io.Source.fromResource("inputs/day01.txt").mkString
    Day02.parse(input) match
      case Right(data) => assertEquals(Day02.part1(data), "663")
      case Left(error) => fail(s"Parse failed: $error")
  }

  test("part2 actual answer") {
    val input = scala.io.Source.fromResource("inputs/day01.txt").mkString
    Day02.parse(input) match
      case Right(data) => assertEquals(Day02.part2(data), "692")
      case Left(error) => fail(s"Parse failed: $error")
  }
