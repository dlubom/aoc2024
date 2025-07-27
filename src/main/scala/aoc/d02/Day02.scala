package aoc.d02

import aoc.core.Day
import cats.parse.{Parser0, Parser as P}
import cats.data.NonEmptyList

object Day02 extends Day[List[List[Int]]]:
  val int: P[Int] = P.charsWhile(_.isDigit).string.map(_.toInt)
  val ws: P[Unit] = P.charIn(" \t").rep.void

  val intRow: P[NonEmptyList[Int]] = int.repSep(ws)
  val allRows: Parser0[List[List[Int]]] =
    intRow.map(_.toList).repSep0(P.char('\n')).map(_.toList)

  def parse(input: String): Either[String, List[List[Int]]] =
    allRows.parseAll(input.trim) match
      case Right(v)  => Right(v)
      case Left(err) => Left(s"Parse error: $err")

  private def isSafe(levels: List[Int]): Boolean = {
    if (levels.length < 2) return false

    val diffs = levels.sliding(2).map { case List(a, b) => b - a }.toList
    val strictlyIncreasing = diffs.forall(_ > 0)
    val strictlyDecreasing = diffs.forall(_ < 0)
    val monotonic = strictlyIncreasing || strictlyDecreasing
    val differencesOK = diffs.forall(d => math.abs(d) >= 1 && math.abs(d) <= 3)

    monotonic && differencesOK
  }
  
  def part1(data: List[List[Int]]): String =
    data.count { levels => isSafe(levels)
    }.toString

  def part2(data: List[List[Int]]): String = {
    data.count { levels =>
      levels.indices.exists { i =>
        val newLevels = levels.take(i) ++ levels.drop(i + 1)
        isSafe(newLevels)
      }
    }.toString
  }