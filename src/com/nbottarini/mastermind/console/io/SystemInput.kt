package com.nbottarini.mastermind.console.io

class SystemInput: Input {
    override fun readLine() = kotlin.io.readLine() ?: ""
}
