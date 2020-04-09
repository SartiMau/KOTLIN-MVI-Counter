package com.globant.counter.mvi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globant.counter.mvi.MviAction
import com.globant.counter.mvi.viewmodel.states.CounterData
import com.globant.counter.mvi.viewmodel.states.CounterState

class MainActivityViewModel : ViewModel() {

    private val mutableMainState: MutableLiveData<CounterData> = MutableLiveData()

    val mainState: MutableLiveData<CounterData>
        get() {
            return mutableMainState
        }

    fun takeAction(action: MviAction) {
        /** Takes action from the Activity and process it **/
        when (action) {
            MviAction.IncrementAction -> handleIncrement()
            MviAction.DecrementAction -> handleDecrement()
            MviAction.ResetAction -> handleReset()
        }
    }

    private fun handleIncrement() {
        val currentValue = mutableMainState.value?.copy() ?: CounterData()
        val newCounterData = CounterData(value = (currentValue.value.toInt() + 1).toString(), state = CounterState.INC)
        mutableMainState.postValue(newCounterData)
    }

    private fun handleDecrement() {
        val currentValue = mutableMainState.value?.copy() ?: CounterData()
        val newCounterData = CounterData(value = (currentValue.value.toInt() - 1).toString(), state = CounterState.DEC)
        mutableMainState.postValue(newCounterData)
    }

    private fun handleReset() {
        mutableMainState.postValue(CounterData(value = "0", state = CounterState.INITIAL))
    }
}
