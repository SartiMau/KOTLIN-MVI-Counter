package com.globant.counter.mvi

sealed class CounterIntent {
    object InitialIntent : CounterIntent()
    object IncrementIntent : CounterIntent()
    object DecrementIntent : CounterIntent()
    object ResetIntent : CounterIntent()
}
