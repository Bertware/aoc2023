package com.github.bertware.aoc2023.day4

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path

class DayFourChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        val score = fun(winningValues: List<String>, chosenValues: List<String>): Int {
            var score = 0
            chosenValues.forEach { score = if (winningValues.contains(it)) if (score == 0) 1 else score * 2 else score }
            return score
        }
        return readLines(path).map {
            val parts = it.split(":")[1].split("|")
            score(parts[0].split(" ").filter { it != "" }, parts[1].split(" ").filter { it != "" })
        }.sum()
    }

    fun part2(path: Path): Int {
        val scratchcards = readLines(path)
        return scratchcards.map { countCards(it, scratchcards) }.sum()
    }

    fun countCards(card: String, allCards: List<String>): Int {
        val wins = fun(card: String): Int {
            val parts = card.split(":")[1].split("|")
            val winningValues = parts[0].split(" ").filter { it != "" }
            return parts[1].split(" ").filter { it != "" }.map { if (winningValues.contains(it)) 1 else 0 }.sum()
        }

        val nextCardIndex = card.split(":")[0].split(" ").filter { it != "" }[1].toInt()  // list is zero-based, so no need to add 1
        val cardWins = wins(card);
        return if (cardWins == 0) 1 else 1 + allCards.subList(nextCardIndex , nextCardIndex  + cardWins).map { countCards(it, allCards) }.sum()
    }
}