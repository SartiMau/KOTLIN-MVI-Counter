package com.globant.counter

class MviActor(private val emit: (CounterIntent) -> Unit) {
    fun incrementClicked() = emit(CounterIntent.IncrementIntent)
    fun decrementClicked() = emit(CounterIntent.DecrementIntent)
    fun resetClicked() = emit(CounterIntent.ResetIntent)
}
