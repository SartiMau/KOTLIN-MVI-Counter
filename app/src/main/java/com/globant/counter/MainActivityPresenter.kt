package com.globant.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainActivityPresenter {

    private val mutableMainState: MutableLiveData<CounterState> = MutableLiveData()
    val mainState: LiveData<CounterState>
        get() {
            return mutableMainState
        }

    // Interpreter
    fun takeIntent(intent: CounterIntent) {
        /** Takes action from the Activity and process it **/
        when (intent) {
            CounterIntent.IncrementIntent -> handleIncrement()
            CounterIntent.DecrementIntent -> handleDecrement()
            CounterIntent.ResetIntent -> handleReset()
            CounterIntent.InitialIntent -> handleReset()
        }
    }

    private fun handleIncrement() {
        val currentValue = mutableMainState.value?.copy() ?: CounterState()
        val newCounterData = CounterState(
            counterCurrentValue = currentValue.counterCurrentValue + 1,
            counterOperation = CounterOperation.INCREMENT
        )
        mutableMainState.postValue(newCounterData)
    }

    private fun handleDecrement() {
        val currentValue = mutableMainState.value?.copy() ?: CounterState()
        val newCounterData = CounterState(
            counterCurrentValue = currentValue.counterCurrentValue - 1,
            counterOperation = CounterOperation.DECREMENT
        )
        mutableMainState.postValue(newCounterData)
    }

    private fun handleReset() {
        mutableMainState.postValue(CounterState())
    }
}
