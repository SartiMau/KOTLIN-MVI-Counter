package com.globant.counter

sealed class CounterIntent {
    object IncrementIntent : CounterIntent()
    object DecrementIntent : CounterIntent()
    object ResetIntent : CounterIntent()
    object InitialIntent : CounterIntent()
}
