package com.nbottarini.mastermind.console.io

class SystemOutput: Output {
    override fun printLine(text: String) {
        println(text)
    }
}
