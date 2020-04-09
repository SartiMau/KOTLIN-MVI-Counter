package com.globant.counter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.globant.counter.CounterOperation
import com.globant.counter.CounterState

@BindingAdapter("customText")
fun TextView.bindText(state: CounterOperation?) {
    text = when(state) {
        CounterOperation.INC -> "👍"
        CounterOperation.DEC -> "️👎"
        else -> ""
    }
}
