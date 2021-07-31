package com.nbottarini.mastermind.game

import com.nbottarini.mastermind.game.codeGenerator.CodeGenerator
import com.nbottarini.mastermind.game.codeGenerator.RandomCodeGenerator
import com.nbottarini.mastermind.game.codes.Code
import com.nbottarini.mastermind.game.codes.Pegs

class Game(codeGenerator: CodeGenerator = RandomCodeGenerator()) {
    private val secretCode = codeGenerator.generate()
    private var remainingAttempts = 10
    var status = GameStatuses.Playing
        private set

    fun guess(code: Code): List<Pegs> {
        failIfNotPlaying()

        if (code == secretCode) {
            status = GameStatuses.PlayerWins
            return listOf()
        }

        decrementAttempts()
        return code.calculatePegsMatching(secretCode)
    }

    private fun failIfNotPlaying() {
        if (status != GameStatuses.Playing) throw GameEndedError()
    }

    private fun decrementAttempts() {
        remainingAttempts--
        if (remainingAttempts == 0) {
            status = GameStatuses.PlayerLoses
        }
    }
}
