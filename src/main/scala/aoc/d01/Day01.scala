package aoc.d01

import aoc.core.Day
import cats.parse.{Parser => P, Parser0}

object Day01 extends Day[Day01.Lists]:

  case class Lists(left: Vector[Long], right: Vector[Long])

  private val num: P[Long] = P.charsWhile(_.isDigit).string.map(_.toLong)

  private val ws: P[Unit] = P.charIn(" \t").rep.void

  private val line: P[(Long, Long)] =
    (num <* ws) ~ num

  private val lines: Parser0[Vector[(Long, Long)]] =
    line.repSep0(P.char('\n')).map(_.toVector)

  def parse(input: String): Either[String, Lists] =
    lines.parseAll(input.trim) match
      case Right(v) =>
        val (l, r) = v.unzip
        Right(Lists(l, r))
      case Left(err) =>
        Left(s"Parse error: $err")

  def part1(data: Lists): String =
    val left = data.left.sorted
    val right = data.right.sorted
    left
      .zip(right)
      .map((l, r) => math.abs(l - r))
      .sum
      .toString

  def part2(data: Lists): String =
    val counts: Map[Long, Int] = data.right.groupBy(identity).view.mapValues(_.size).toMap

    data.left
      .map(l => l * counts.getOrElse(l, 0))
      .sum
      .toString
