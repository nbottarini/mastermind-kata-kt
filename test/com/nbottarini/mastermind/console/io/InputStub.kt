@file:Suppress("PrivatePropertyName")

package com.nbottarini.mastermind.console.io

class InputStub: Input {
    private var nextInputs = mutableListOf<String>()
    private var currentIndex = 0
    private val DEFAULT_INPUT = "blue, blue, blue, blue"

    fun willRead(input: String) {
        nextInputs.add(input)
    }

    override fun readLine(): String {
        return nextInputs.getOrNull(currentIndex++) ?: DEFAULT_INPUT
    }
}
