package com.globant.counter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("customText")
fun TextView.bindText(state: CounterOperation?) {
    text = when(state) {
        CounterOperation.INCREMENT -> "👍"
        CounterOperation.DECREMENT -> "️👎"
        else -> "😃"
    }
}
