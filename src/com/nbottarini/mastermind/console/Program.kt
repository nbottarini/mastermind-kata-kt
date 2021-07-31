package com.nbottarini.mastermind.console

import com.nbottarini.mastermind.game.*
import com.nbottarini.mastermind.game.codes.InvalidCodeError
import com.nbottarini.mastermind.console.io.Input
import com.nbottarini.mastermind.console.io.Output
import com.nbottarini.mastermind.console.io.SystemInput
import com.nbottarini.mastermind.console.io.SystemOutput
import com.nbottarini.mastermind.game.codes.Code
import com.nbottarini.mastermind.game.codes.Pegs

class Program(
    input: Input = SystemInput(),
    private val output: Output = SystemOutput(),
    private val game: Game = Game()
) {
    private val codeReader = CodeReader(input)

    fun run() {
        printWelcome()
        do {
            val code = readCode()
            val pegs = game.guess(code)
            printPegs(pegs)
        } while(gameNotEnded())
        printGameResult()
    }

    private fun printWelcome() {
        output.printLine("Welcome to Mastermind!")
        output.printLine("Try to guess the combination using the colours (red, blue, green, orange, purple, yellow):")
    }

    private fun readCode(): Code {
        while(true) {
            try {
                return codeReader.readCode()
            } catch(e: InvalidCodeError) {
                output.printLine("Invalid guess")
            }
        }
    }

    private fun printPegs(pegs: List<Pegs>) {
        output.printLine(pegs.joinToString(", ") { it.name.lowercase() })
    }

    private fun gameNotEnded() = game.status == GameStatuses.Playing

    private fun printGameResult() {
        when (game.status) {
            GameStatuses.PlayerWins -> output.printLine("YOU WON")
            GameStatuses.PlayerLoses -> output.printLine("GAME OVER")
            else -> {}
        }
    }
}
