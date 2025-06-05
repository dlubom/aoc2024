package aoc.core

import scala.io.Source
import scala.concurrent.duration.*

object Runner:

  private val days: Map[Int, Day[?]] =
    Map(
      1 -> aoc.d01.Day01
      // 2 -> aoc.d02.Day02,
      // …
    )

  @main def run(args: String*): Unit =
    val totalStartTime = System.nanoTime()

    val toRun =
      if args.isEmpty || args.head == "all" then days.keys
      else args.map(_.toInt)

    for day <- toRun.toList.sorted do
      days.get(day) match
        case Some(d) =>
          val input = Source.fromResource(f"inputs/day$day%02d.txt").mkString
          val dayStartTime = System.nanoTime()
          d.run(input) match
            case Right((p1, p2)) =>
              val dayDuration = (System.nanoTime() - dayStartTime).nanos
              println(s"Day $day - part 1: $p1")
              println(s"Day $day - part 2: $p2")
              println(s"Day $day - time: ${formatDuration(dayDuration)}")
            case Left(err) =>
              val dayDuration = (System.nanoTime() - dayStartTime).nanos
              println(s"Error running Day $day: $err")
              println(s"Day $day - time: ${formatDuration(dayDuration)}")
        case None =>
          println(s"Day $day is not implemented.")

    val totalDuration = (System.nanoTime() - totalStartTime).nanos
    println(s"\nTotal execution time: ${formatDuration(totalDuration)}")

  private def formatDuration(duration: Duration): String =
    if duration.toMillis < 1000 then f"${duration.toMillis}%dms"
    else f"${duration.toMillis / 1000.0}%.3fs"
