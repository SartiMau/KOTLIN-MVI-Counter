package com.globant.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<CounterState> = MutableLiveData()
    private var initialized: Boolean = false

    fun getValue(): MutableLiveData<CounterState> {
        return mutableLiveData
    }

    // Interpreter
    fun takeIntent(intent: CounterIntent) {
        /** Takes action from the Activity and process it **/
        when (intent) {
            CounterIntent.InitialIntent -> if (!initialized) {
                processor(CounterAction.InitValue)
                initialized = true
            }
            CounterIntent.IncrementIntent -> processor(CounterAction.IncValue())
            CounterIntent.DecrementIntent -> processor(CounterAction.DecValue())
            CounterIntent.ResetIntent -> processor(CounterAction.RstValue())
        }
    }

    // Processor
    private fun processor(action: CounterAction) {

        val result = when (action) {
            is CounterAction.InitValue -> CounterResult.InitValueResult()
            is CounterAction.IncValue -> CounterResult.IncResult(CounterState(action.value))
            is CounterAction.DecValue -> CounterResult.DecResult(CounterState(action.value))
            is CounterAction.RstValue -> CounterResult.RstResult(CounterState(action.value))
        }
        reducer(mutableLiveData.value, result)
    }

    // Reducer
    private fun reducer(previousState: CounterState?, result: CounterResult) {
        val newState = CounterState()
        when (result) {
            is CounterResult.RstResult -> {
                newState.counterOperation = CounterOperation.RST
            }
            is CounterResult.IncResult -> {
                newState.counterCurrentValue = previousState?.counterCurrentValue?.plus(result.state.counterCurrentValue) ?: 0
                newState.counterOperation = CounterOperation.INC

            }
            is CounterResult.DecResult -> {
                newState.counterCurrentValue = previousState?.counterCurrentValue?.plus(result.state.counterCurrentValue) ?: 0
                newState.counterOperation = CounterOperation.DEC
            }
        }
        mutableLiveData.value = newState
    }
}
