package com.globant.counter.mvi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globant.counter.mvi.model.CountModel
import com.globant.counter.mvi.viewmodel.states.CounterData
import com.globant.counter.mvi.viewmodel.states.CounterState

class MainActivityViewModel : ViewModel() {

    private val model = CountModel()

    private val mutableLiveData: MutableLiveData<CounterData> = MutableLiveData()

    fun getValue(): MutableLiveData<CounterData> {
        return mutableLiveData
    }

    fun resetValue() {
        model.reset()
        mutableLiveData.value = CounterData(CounterState.INITIAL)
    }

    fun incValue() {
        model.inc()
        mutableLiveData.value = CounterData(CounterState.INC, model.count)
    }

    fun decValue() {
        model.dec()
        mutableLiveData.value = CounterData(CounterState.DEC, model.count)
    }
}
