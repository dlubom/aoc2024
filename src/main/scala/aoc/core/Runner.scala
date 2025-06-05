package aoc.core

import scala.io.Source

object Runner:

  private val days: Map[Int, Day[?]] =
    Map(
      1 -> aoc.d01.Day01
      // 2 -> aoc.d02.Day02,
      // …
    )

  @main def run(args: String*): Unit =
    val toRun =
      if args.isEmpty || args.head == "all" then days.keys
      else args.map(_.toInt)

    for day <- toRun.toList.sorted do
      days.get(day) match
        case Some(d) =>
          val input = Source.fromResource(f"inputs/day$day%02d.txt").mkString
          d.run(input) match
            case Right((p1, p2)) =>
              println(s"Day $day - part 1: $p1")
              println(s"Day $day - part 2: $p2")
            case Left(err) =>
              println(s"Error running Day $day: $err")
        case None =>
          println(s"Day $day is not implemented.")
