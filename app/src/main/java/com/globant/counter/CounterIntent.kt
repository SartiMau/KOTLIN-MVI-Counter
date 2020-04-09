package com.globant.counter

sealed class CounterIntent {
    object IncrementIntent : CounterIntent()
    object InitialIntent : CounterIntent()
    object DecrementIntent : CounterIntent()
    object ResetIntent : CounterIntent()
}
