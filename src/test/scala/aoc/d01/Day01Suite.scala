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
