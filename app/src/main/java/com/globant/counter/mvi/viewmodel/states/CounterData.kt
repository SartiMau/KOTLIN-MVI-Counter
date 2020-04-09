package com.globant.counter.mvi.viewmodel.states

data class CounterData(
    val state: CounterState = CounterState.INITIAL,
    val value: String = "0"
)
