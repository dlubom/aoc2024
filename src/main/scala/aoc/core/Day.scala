package aoc.core

trait Day[T]:
  def parse(input: String): T
  def part1(data: T): String
  def part2(data: T): String

  final def run(raw: String): (String, String) =
    val parsed = parse(raw.trim)
    (part1(parsed), part2(parsed))
