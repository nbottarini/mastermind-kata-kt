package com.nbottarini.mastermind.game.codes

import com.nbottarini.mastermind.game.codes.Pegs.Black
import com.nbottarini.mastermind.game.codes.Pegs.White

class Code(private val colors: List<Colors>): List<Colors> by colors {
    constructor(vararg colors: Colors): this(colors.toList())

    init {
        if (colors.size != 4) throw InvalidCodeError()
    }

    fun calculatePegsMatching(other: Code): List<Pegs> {
        val exactColors = colorsInSamePosition(other)
        val sameColors = sameColors(other) - exactColors
        return exactColors.map { Black } + sameColors.map { White }
    }

    private fun colorsInSamePosition(other: Code) = colors.indices.filter { colors[it] == other.colors[it] }

    private fun sameColors(other: Code) = colors.indices.filter { other.colors.contains(colors[it]) }

    override fun equals(other: Any?) = other is Code && other.colors == colors

    override fun hashCode() = colors.hashCode()

    override fun toString() = "Code($colors)"
}
