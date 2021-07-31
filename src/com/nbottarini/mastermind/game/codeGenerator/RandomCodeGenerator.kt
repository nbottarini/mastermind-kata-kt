package com.nbottarini.mastermind.game.codeGenerator

import com.nbottarini.mastermind.game.codes.Code
import com.nbottarini.mastermind.game.codes.Colors

class RandomCodeGenerator: CodeGenerator {
    override fun generate() =
        Code(Colors.values().random(), Colors.values().random(), Colors.values().random(), Colors.values().random())
}
