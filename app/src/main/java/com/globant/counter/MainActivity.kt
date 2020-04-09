package com.globant.counter

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.globant.counter.databinding.ActivityMainBinding
import com.globant.counter.mvi.MviActor
import com.globant.counter.mvi.viewmodel.MainActivityViewModel
import com.globant.counter.mvi.viewmodel.states.CounterData

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val actor = MviActor(viewModel::takeAction)
        binding.actor = actor
        binding.notifyPropertyChanged(BR.actor)

        observeData(binding, viewModel)
    }

    private fun observeData(binding: ActivityMainBinding, viewModel: MainActivityViewModel) {
        val stateObserver = Observer<CounterData> {
            // Binds state changes to the view.
            binding.mainState = it
            binding.notifyPropertyChanged(BR.mainState)

            /*
            when (it.state) {
                CounterState.INITIAL ->  showToast(getString(R.string.main_activity_toast_reset_text))
                CounterState.INC -> showToast(getString(R.string.main_activity_toast_incremented_text))
                CounterState.DEC -> showToast(getString(R.string.main_activity_toast_decremented_text))
            }
             */
        }

        viewModel.mainState.observe(this, stateObserver)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
