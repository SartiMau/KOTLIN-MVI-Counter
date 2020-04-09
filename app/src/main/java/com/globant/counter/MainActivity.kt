package com.globant.counter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.countBtnDec
import kotlinx.android.synthetic.main.activity_main.countBtnInc
import kotlinx.android.synthetic.main.activity_main.countLabel
import kotlinx.android.synthetic.main.activity_main.resetBtn

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        viewModel.getValue().observe(this, Observer { updateUI(it) })

        // Move to Data binding {
        initListeners()
        viewModel.sendIntent(CounterIntent.InitialIntent)
        // }
    }

    // Move to Data binding {
    private fun initListeners() {
        countBtnInc.setOnClickListener {
            viewModel.sendIntent(CounterIntent.IncrementIntent)
        }
        countBtnDec.setOnClickListener {
            viewModel.sendIntent(CounterIntent.DecrementIntent)
        }
        resetBtn.setOnClickListener {
            viewModel.sendIntent(CounterIntent.ResetIntent)
        }
    }
    // }

    private fun updateUI(counterState: CounterState) {
        // Move to Data binding {
        countLabel.text = counterState.counterCurrentValue.toString()
        // }
        when (counterState.counterOperation) {
            CounterOperation.INC -> showToast(getString(R.string.main_activity_toast_incremented_text))
            CounterOperation.DEC -> showToast(getString(R.string.main_activity_toast_decremented_text))
            CounterOperation.RST -> showToast(getString(R.string.main_activity_toast_reset_text))
            else -> {
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
