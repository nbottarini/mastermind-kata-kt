package com.nbottarini.mastermind.game.codes

enum class Colors {
    Red, Blue, Green, Orange, Purple, Yellow;

    companion object {
        fun fromString(value: String): Colors {
            return values().singleOrNull { it.name.equals(value, ignoreCase = true) } ?: throw InvalidColorError(value)
        }
    }
}
