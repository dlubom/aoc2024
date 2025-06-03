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
    assertEquals(Day01.part1(Day01.parse(sample)), "11")
  }

  test("part2 sample similarity = 31") {
    assertEquals(Day01.part2(Day01.parse(sample)), "31")
  }

  // --- Regression tests on the full input ---------------------------------
  test("part1 actual answer") {
    val input = scala.io.Source.fromResource("inputs/day01.txt").mkString
    assertEquals(Day01.part1(Day01.parse(input)), "1873376")
  }

  test("part2 actual answer") {
    val input = scala.io.Source.fromResource("inputs/day01.txt").mkString
    assertEquals(Day01.part2(Day01.parse(input)), "18997088")
  }
