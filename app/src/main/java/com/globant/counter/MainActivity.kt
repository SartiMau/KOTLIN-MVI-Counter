package com.globant.counter

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.globant.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainActivityPresenter()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val actor = MviActor(presenter::takeIntent)
        binding.actor = actor
        binding.notifyPropertyChanged(BR.actor)

        presenter.mainState.observe(this, Observer { updateUI(it) })
    }

    private fun updateUI(data: CounterState) {
        // Binds state changes to the view.
        binding.mainState = data
        binding.notifyPropertyChanged(BR.mainState)

        when (data.counterOperation) {
            CounterOperation.INCREMENT -> showToast(getString(R.string.main_activity_toast_incremented_text))
            CounterOperation.DECREMENT -> showToast(getString(R.string.main_activity_toast_decremented_text))
            CounterOperation.RESET -> showToast(getString(R.string.main_activity_toast_reset_text))
            CounterOperation.INITIAL -> showToast(getString(R.string.main_activity_toast_reset_text))
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
