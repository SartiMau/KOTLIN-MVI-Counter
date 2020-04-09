package com.globant.counter

sealed class CounterResult {
    data class InitValueResult(private val state: CounterState = CounterState()) : CounterResult()
    data class IncResult(val state: CounterState) : CounterResult()
    data class DecResult(val state: CounterState) : CounterResult()
    data class RstResult(val state: CounterState) : CounterResult()
}
