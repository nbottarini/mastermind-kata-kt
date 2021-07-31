package com.nbottarini.mastermind.game.codeGenerator

import com.nbottarini.mastermind.game.codes.Code

interface CodeGenerator {
    fun generate(): Code
}
