package com.nbottarini.mastermind.game.codeGenerator

import com.nbottarini.mastermind.game.codes.Code

class StaticCodeGenerator(private val code: Code): CodeGenerator {
    override fun generate() = code
}
