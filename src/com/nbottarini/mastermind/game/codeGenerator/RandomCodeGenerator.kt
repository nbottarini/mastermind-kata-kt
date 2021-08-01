package com.nbottarini.mastermind.game.codeGenerator

import com.nbottarini.mastermind.game.codes.Code
import com.nbottarini.mastermind.game.codes.Colors

class RandomCodeGenerator: CodeGenerator {
    private val colors = Colors.values()

    override fun generate() = Code(colors.random(), colors.random(), colors.random(), colors.random())
}
