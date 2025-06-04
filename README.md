# Advent of Code 2024 (aoc2024)

This repository contains solutions for the [Advent of Code 2024](https://adventofcode.com/2024) puzzles, implemented in Scala 3 and managed with sbt.

## Project Structure

- `src/main/scala/aoc/dXX/DayXX.scala` – Solution for Day XX of the puzzle.
- `src/main/resources/inputs/dayXX.txt` – Input file for Day XX.
- additional directories `d01`, `d02`, ... will appear as more days are solved.

## Requirements

- Java JDK 11 or newer
- sbt 1.11+

## Usage

Compile all solutions:

```shell
sbt compile
```

Run tests:

```shell
sbt test
```

Run a specific day (e.g. Day 3):

```shell
sbt "run 3"
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
