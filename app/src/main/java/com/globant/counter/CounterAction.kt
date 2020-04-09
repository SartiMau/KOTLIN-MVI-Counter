package com.globant.counter

sealed class CounterAction {
    object InitValue : CounterAction()
    data class IncValue( val value : Int = 1) : CounterAction()
    data class DecValue( val value : Int = -1) : CounterAction()
    data class RstValue(val value: Int = 0) : CounterAction()
}
