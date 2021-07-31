package com.nbottarini.mastermind.console.io

import com.nbottarini.mastermind.console.io.Output

class OutputSpy: Output {
    var contents = ""
        private set

    override fun printLine(text: String) {
        contents += text + "\n"
    }
}
