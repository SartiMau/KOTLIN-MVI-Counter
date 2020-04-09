package com.globant.counter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.counter.mvi.viewmodel.MainActivityViewModel
import com.globant.counter.mvi.viewmodel.states.CounterState
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: MainActivityViewModel = MainActivityViewModel()

    @Test
    fun initialStateTest() {
        assert(viewModel.mainState.value == null)
    }

    @Test
    fun onPressResetAfterInitialStateTest() {
        viewModel.resetValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.INITIAL)
        assert(viewModel.mainState.value?.value == ZERO)
    }

    @Test
    fun onPressResetAfterPressIncTest() {
        viewModel.incValue()
        viewModel.resetValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.INITIAL)
        assert(viewModel.mainState.value?.value == ZERO)
    }

    @Test
    fun onPressResetAfterPressDecTest() {
        viewModel.decValue()
        viewModel.resetValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.INITIAL)
        assert(viewModel.mainState.value?.value == ZERO)
    }

    @Test
    fun onPressIncAfterInitialStateTest() {
        viewModel.incValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.INC)
        assert(viewModel.mainState.value?.value == ONE)
    }

    @Test
    fun onPressDecAfterInitialStateTest() {
        viewModel.decValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.DEC)
        assert(viewModel.mainState.value?.value == MINUS_ONE)
    }

    @Test
    fun onPressDecAfterPressIncTest() {
        viewModel.incValue()
        viewModel.decValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.DEC)
        assert(viewModel.mainState.value?.value == ZERO)
    }

    @Test
    fun onPressIncAfterPressDecTest() {
        viewModel.decValue()
        viewModel.incValue()

        assert(viewModel.mainState.value != null)
        assert(viewModel.mainState.value?.state == CounterState.INC)
        assert(viewModel.mainState.value?.value == ZERO)
    }

    companion object {
        private const val ZERO = 0
        private const val MINUS_ONE = -1
        private const val ONE = 1
    }
}
