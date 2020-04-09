package com.globant.counter.mvi

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.globant.counter.mvi.viewmodel.states.CounterState

@BindingAdapter("customText")
fun TextView.bindText(state: CounterState?) {
    text = when(state) {
        CounterState.INC -> "👍"
        CounterState.DEC -> "️👎"
        else -> ""
    }
}
