package com.nbottarini.mastermind.game

import com.nbottarini.mastermind.game.codes.Colors.*
import com.nbottarini.mastermind.game.codes.Pegs.Black
import com.nbottarini.mastermind.game.codes.Pegs.White
import com.nbottarini.mastermind.game.codeGenerator.StaticCodeGenerator
import com.nbottarini.mastermind.game.codes.Code
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {
    @Test
    fun `win if guess secret`() {
        val game = game(secretCode)

        game.guess(secretCode)

        assertThat(game.status).isEqualTo(GameStatuses.PlayerWins)
    }

    @Test
    fun `return no pegs if guess doesn't have any color from secret code`() {
        val game = game(secretCode = Code(Blue, Blue, Blue, Blue))

        val pegs = game.guess(Code(Red, Red, Red, Red))

        assertThat(game.status).isEqualTo(GameStatuses.Playing)
        assertThat(pegs).isEmpty()
    }

    @Test
    fun `return a white peg for each color present in secret code but not in same position`() {
        val game = game(secretCode = Code(Blue, Blue, Red, Red))

        val pegs = game.guess(Code(Red, Red, Yellow, Yellow))

        assertThat(pegs).containsExactly(White, White)
    }

    @Test
    fun `return a black peg for each color present in secret code in same position`() {
        val game = game(secretCode = Code(Blue, Blue, Red, Red))

        val pegs = game.guess(Code(Blue, Blue, Yellow, Red))

        assertThat(pegs).containsExactly(Black, Black, Black)
    }

    @Test
    fun `return all black pegs first`() {
        val game = game(secretCode = Code(Blue, Blue, Red, Red))

        val pegs = game.guess(Code(Blue, Red, Red, Blue))

        assertThat(pegs).containsExactly(Black, Black, White, White)
    }

    @Test
    fun `looses games after 10 incorrect guesses`() {
        val game = game(secretCode = Code(Blue, Blue, Red, Red))

        repeat(10) { game.guess(Code(Red, Red, Red, Red)) }

        assertThat(game.status).isEqualTo(GameStatuses.PlayerLoses)
    }

    @Test
    fun `cannot play if game is won`() {
        assertThrows<GameEndedError> { aWonGame().guess(Code(Red, Red, Red, Red)) }
    }

    @Test
    fun `cannot play if game is lost`() {
        assertThrows<GameEndedError> { aLostGame().guess(Code(Red, Red, Red, Red)) }
    }

    @Test
    fun `can win on last guess`() {
        val game = game(secretCode = Code(Blue, Blue, Blue, Blue))
        repeat(9) { game.guess(Code(Red, Red, Red, Red)) }

        game.guess(Code(Blue, Blue, Blue, Blue))

        assertThat(game.status).isEqualTo(GameStatuses.PlayerWins)
    }

    private fun aWonGame(): Game {
        val game = game(secretCode)
        game.guess(secretCode)
        return game
    }

    private fun aLostGame(): Game {
        val game = game(secretCode = Code(Blue, Blue, Blue, Blue))
        repeat(10) { game.guess(Code(Red, Red, Red, Red)) }
        return game
    }

    private fun game(secretCode: Code) = Game(StaticCodeGenerator(secretCode))

    private val secretCode = Code(Blue, Blue, Blue, Blue)
}
