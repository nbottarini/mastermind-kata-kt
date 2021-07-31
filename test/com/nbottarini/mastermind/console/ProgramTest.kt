@file:Suppress("PrivatePropertyName")

package com.nbottarini.mastermind.console

import com.nbottarini.mastermind.console.io.InputStub
import com.nbottarini.mastermind.console.io.OutputSpy
import com.nbottarini.mastermind.game.codes.Code
import com.nbottarini.mastermind.game.codes.Colors.*
import com.nbottarini.mastermind.game.Game
import com.nbottarini.mastermind.game.GameStatuses
import com.nbottarini.mastermind.game.codes.Pegs.Black
import com.nbottarini.mastermind.game.codes.Pegs.White
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ProgramTest {
    @Test
    fun `shows welcome message on start`() {
        program.run()

        assertThat(output.contents).startsWith(welcomeMessage)
    }

    @Test
    fun `processes a player guess`() {
        input.willRead("blue, red, green, yellow")

        program.run()

        verify { game.guess(Code(Blue, Red, Green, Yellow)) }
    }

    @Test
    fun `show message if game won`() {
        every { game.status } returns GameStatuses.PlayerWins
        input.willRead(SOME_CODE)

        program.run()

        assertThat(output.contents.lastLine()).isEqualTo("YOU WON")
    }

    @Test
    fun `show message if game lost`() {
        every { game.status } returns GameStatuses.PlayerLoses
        input.willRead(SOME_CODE)

        program.run()

        assertThat(output.contents.lastLine()).isEqualTo("GAME OVER")
    }

    @Test
    fun `show pegs after wrong guess`() {
        every { game.guess(any()) } returns listOf(Black, Black, White)
        input.willRead(SOME_CODE)

        program.run()

        assertThat(output.contents.lineAfterWelcome()).isEqualTo("black, black, white")
    }

    @Test
    fun `read many guesses`() {
        every { game.status } returnsMany listOf(GameStatuses.Playing, GameStatuses.PlayerLoses)
        input.willRead("red, blue, green, orange")
        input.willRead("purple, yellow, red, blue")

        program.run()

        verifyOrder {
            game.guess(Code(Red, Blue, Green, Orange))
            game.guess(Code(Purple, Yellow, Red, Blue))
        }
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "",
        ",,,",
        "blue, blue, blue, invalid",
        "blue, blue, blue",
        "blue, blue, blue, blue, blue",
    ])
    fun `print invalid guess on invalid code`(code: String) {
        input.willRead(code)

        program.run()

        assertThat(output.contents.lineAfterWelcome()).isEqualTo("Invalid guess")
    }

    @BeforeEach
    fun beforeEach() {
        every { game.status } returns GameStatuses.PlayerWins
    }

    private fun String.lineAfterWelcome() = this.removePrefix(welcomeMessage).lines().first()

    private fun String.lastLine() = this.removeSuffix("\n").lines().last()

    private val SOME_CODE = "blue, red, green, yellow"
    private val welcomeMessage = "Welcome to Mastermind!\nTry to guess the combination using the colours (red, blue, green, orange, purple, yellow):\n"
    private val input = InputStub()
    private val output = OutputSpy()
    private val game = mockk<Game>(relaxed = true)
    private val program = Program(input, output, game)
}
