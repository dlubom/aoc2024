package aoc.d01

import aoc.core.Day
import cats.parse.{Parser => P, Parser0}

object Day01 extends Day:

  case class Lists(left: Vector[Long], right: Vector[Long])

  private val num: P[Long] = P.charsWhile(_.isDigit).string.map(_.toLong)

  private val ws: P[Unit] = P.charIn(" \t").rep.void

  private val line: P[(Long, Long)] =
    (num <* ws) ~ num

  private val lines: Parser0[Vector[(Long, Long)]] =
    line.repSep0(P.char('\n')).map(_.toVector)

  def parse(input: String): Lists =
    lines.parseAll(input.trim) match
      case Right(v) =>
        val (l, r) = v.unzip
        Lists(l, r)
      case Left(err) =>
        throw new RuntimeException(s"Parse error: $err")

  def part1(data: Any): String =
    val lists = data.asInstanceOf[Lists]
    val left = lists.left.sorted
    val right = lists.right.sorted
    left
      .zip(right)
      .map((l, r) => math.abs(l - r))
      .sum
      .toString

  def part2(data: Any): String =
    val lists = data.asInstanceOf[Lists]
    val counts: Map[Long, Int] = lists.right.groupBy(identity).view.mapValues(_.size).toMap

    lists.left
      .map(l => l * counts.getOrElse(l, 0))
      .sum
      .toString
