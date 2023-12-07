package com.github.bertware.aoc2023.day7

import com.github.bertware.aoc2023.AocChallenge
import java.nio.file.Path

private const val HAND1_BETTER = 1
private const val HAND2_BETTER = -1
private const val HANDS_EQUAL = 0

class DaySevenChallenge : AocChallenge() {

    fun part1(path: Path): Int {
        var hands = readLines(path).map { HandAndBid(it.split(" ")[0], it.split(" ")[1].toInt()) }.toList()
        hands = hands.sortedWith { h1, h2 -> h1.compareTo(h2) }
        var result = 0;
        for (i in 1..hands.size)
            result += hands[i - 1].bid * i
        return result
    }


    fun part2(path: Path): Int {
        return 0
    }

    class HandAndBid(val hand: String, val bid: Int) {

        val type: String = getType(hand)

        private fun getType(hand: String): String {
            val hand1cards = HashMap<String, Int>()

            hand.forEach {
                hand1cards[it.toString()] = hand1cards.getOrDefault(it.toString(), 0) + 1
            }

            var h1max = hand1cards.values.max()
            val hand1TopCard =
                hand1cards.filter { it.value == h1max }.map { it.key }.sortedWith { o1, o2 -> (-compareCards(o1, o2)) }[0]
            val h1max2 = hand1cards.filter { it.key != hand1TopCard }.map { it.value }.maxOrNull() ?: 0

            if (h1max == 5)
                return "7FIVE"

            if (h1max == 4)
                return "6FOUR"

            if (h1max == 3 && h1max2 == 2)
                return "5FHOUSE"

            if (h1max == 3)
                return "4THREEK"

            if (h1max == 2 && h1max2 == 2)
                return "3TWOPAIR"

            if (h1max == 2)
                return "2PAIR"

            return "1HIGH"
        }

        fun compareTo(other: HandAndBid): Int {
            if (type == other.type)
                return compareHandsEachCard(hand, other.hand);
            else
                return type.compareTo(other.type)
        }

        fun compareCards(c1: String, c2: String): Int {
            if (getCardValue(c1) > getCardValue(c2))
                return HAND1_BETTER
            if (getCardValue(c1) < getCardValue(c2))
                return HAND2_BETTER
            return HANDS_EQUAL
        }

        fun compareHandsEachCard(h1: String, h2: String): Int {
            for (i in 0..h1.length) {
                val compResult = compareCards(h1[i].toString(), h2[i].toString());
                if (compResult != 0)
                    return compResult
            }
            return HANDS_EQUAL
        }


        private fun getCardValue(card: String): Int {
            return when (card) {
                "A" -> 14
                "K" -> 13
                "Q" -> 12
                "T" -> 11
                "J" -> 0
                else -> {
                    card.toInt()
                }
            }
        }

        override fun toString(): String {
            return "$hand ($bid) $type"
        }
    }
}