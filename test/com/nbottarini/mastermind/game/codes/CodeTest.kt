package com.nbottarini.mastermind.game.codes

import com.nbottarini.mastermind.game.codes.Colors.Blue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CodeTest {
    @Test
    fun `fail if length is not 4`() {
        assertThrows<InvalidCodeError> { Code(Blue, Blue, Blue) }
        assertThrows<InvalidCodeError> { Code(Blue, Blue, Blue, Blue, Blue) }
    }
}
