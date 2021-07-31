package com.nbottarini.mastermind.game.codes

class InvalidColorError(value: String): Throwable("Color '$value' is invalid")
