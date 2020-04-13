package com.globant.counter

data class CounterState(
    var counterCurrentValue: Int = 0,
    var counterOperation: CounterOperation = CounterOperation.INITIAL
) {
    fun getCurrentValueString() = counterCurrentValue.toString()
}
