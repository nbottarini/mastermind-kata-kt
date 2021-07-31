package com.nbottarini.mastermind.console

import com.nbottarini.mastermind.console.io.Input
import com.nbottarini.mastermind.game.codes.Code
import com.nbottarini.mastermind.game.codes.Colors
import com.nbottarini.mastermind.game.codes.InvalidCodeError
import com.nbottarini.mastermind.game.codes.InvalidColorError

class CodeReader(private val input: Input) {
    fun readCode() = try {
        tryReadCode()
    } catch(e: InvalidColorError) {
        throw InvalidCodeError()
    }

    private fun tryReadCode() = codeFromString(input.readLine())

    private fun codeFromString(text: String): Code {
        val colorNames = text.split(",").map { it.trim()}
        return Code(colorNames.map { Colors.fromString(it) })
    }
}
