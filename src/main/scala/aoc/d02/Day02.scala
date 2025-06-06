package aoc.d02

import aoc.core.Day
import cats.parse.{Parser, Parser0, Numbers, Rfc5234}
import cats.data.NonEmptyList

object Day02 extends Day[Day02.Data]:

  case class Data(list: List[List[Int]])

  val intParser: Parser[Int] = Numbers.digits.map(_.toInt)
  val spaces: Parser[Unit] = Rfc5234.sp.rep(1).void
  val lineOfIntsParser: Parser[List[Int]] = intParser.repSep(spaces).map(_.toList)
  val eol: Parser[Unit] = Parser.char('\n') | Rfc5234.crlf
  val fileParser: Parser0[List[List[Int]]] = lineOfIntsParser.repSep0(eol).map(_.toList)
  private val allowedIncreasing = Set(1, 2, 3)
  private val allowedDecreasing = Set(-1, -2, -3)

  def parse(input: String): Either[String, Data] =
    fileParser.parseAll(input.trim) match
      case Right(v) =>
        Right(Data(v))
      case Left(err) =>
        Left(s"Parse error: $err")

  private def isSafe(row: List[Int]): Boolean =
    val diffs = row.iterator.sliding(2).map { case Seq(a, b) => b - a }.toSet
    diffs.nonEmpty && (diffs.subsetOf(allowedIncreasing) || diffs.subsetOf(allowedDecreasing))

  def part1(data: Data): String =
    data.list.count(isSafe).toString

  def part2(data: Data): String =
    val list = data.list
    list.length.toString
