package aoc.core

trait Day:
  def parse(input: String): Any
  def part1(data: Any): String
  def part2(data: Any): String

  final def run(raw: String): (String, String) =
    val parsed = parse(raw.trim)
    (part1(parsed), part2(parsed))
