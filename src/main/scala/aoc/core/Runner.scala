package aoc.core

import scala.io.Source

object Runner:

  private val days: Map[Int, Day[?]] =
    Map(1 -> aoc.d01.Day01 /*, 2 -> Day02 … */ )

  @main def run(day: Int): Unit =
    val d = days(day)

    val input = Source.fromResource(s"inputs/day${day.formatted("%02d")}.txt").mkString
    val (p1, p2) = d.run(input)
    println(s"Day $day - part 1: $p1")
    println(s"Day $day - part 2: $p2")
