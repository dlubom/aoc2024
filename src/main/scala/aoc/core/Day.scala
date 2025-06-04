package aoc.core

trait Day[T]:
  def parse(input: String): Either[String, T]
  def part1(data: T): String
  def part2(data: T): String

  final def run(raw: String): Either[String, (String, String)] =
    parse(raw.trim) match
      case Right(parsed) => Right((part1(parsed), part2(parsed)))
      case Left(error)   => Left(error)
