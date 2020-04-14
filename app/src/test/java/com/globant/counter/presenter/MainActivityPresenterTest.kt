package com.globant.counter.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.counter.CounterIntent
import com.globant.counter.CounterOperation
import com.globant.counter.CounterState
import com.globant.counter.MainActivityPresenter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityPresenterTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val presenter: MainActivityPresenter = MainActivityPresenter()
    /**
     * Testability —
     * all we have to do to write the unit test for our app is call a proper business method and check if we’ve got a proper state.
     *
     **/

    @Before
    fun setup() {
        presenter.takeIntent(CounterIntent.InitialIntent)
    }

    @Test
    fun initialStateTest() {
        val expectedState = CounterState(counterCurrentValue = ZERO, counterOperation = CounterOperation.INITIAL)
        assert(presenter.mainState.value == expectedState)
    }

    @Test
    fun onPressResetAfterInitialStateTest() {
        val expectedState = CounterState(counterCurrentValue = ZERO, counterOperation = CounterOperation.INITIAL)

        assert(expectedState == presenter.mainState.value)
    }

    @Test
    fun onPressResetAfterPressIncTest() {
        val expectedState = CounterState(counterCurrentValue = ZERO, counterOperation = CounterOperation.INITIAL)
        presenter.takeIntent(CounterIntent.IncrementIntent)
        presenter.takeIntent(CounterIntent.ResetIntent)
        assert(expectedState == presenter.mainState.value)
    }

    @Test
    fun onPressResetAfterPressDecTest() {
        val expectedState = CounterState(counterCurrentValue = ZERO, counterOperation = CounterOperation.INITIAL)
        presenter.takeIntent(CounterIntent.DecrementIntent)
        presenter.takeIntent(CounterIntent.ResetIntent)
        assert(expectedState == presenter.mainState.value)
    }

    @Test
    fun onPressIncAfterInitialStateTest() {
        val expectedState = CounterState(counterCurrentValue = ONE, counterOperation = CounterOperation.INCREMENT)

        presenter.takeIntent(CounterIntent.IncrementIntent)
        assert(expectedState == presenter.mainState.value)
    }

    @Test
    fun onPressDecAfterInitialStateTest() {
        val expectedState = CounterState(counterCurrentValue = MINUS_ONE, counterOperation = CounterOperation.DECREMENT)

        presenter.takeIntent(CounterIntent.DecrementIntent)
        assert(expectedState == presenter.mainState.value)
    }

    @Test
    fun onPressDecAfterPressIncTest() {
        val expectedState = CounterState(counterCurrentValue = ZERO, counterOperation = CounterOperation.DECREMENT)

        presenter.takeIntent(CounterIntent.IncrementIntent)
        presenter.takeIntent(CounterIntent.DecrementIntent)
        assert(expectedState == presenter.mainState.value)
    }

    @Test
    fun onPressIncAfterPressDecTest() {
        val expectedState = CounterState(counterCurrentValue = ZERO, counterOperation = CounterOperation.INCREMENT)

        presenter.takeIntent(CounterIntent.DecrementIntent)
        presenter.takeIntent(CounterIntent.IncrementIntent)
        assert(expectedState == presenter.mainState.value)
    }

    companion object {
        private const val ZERO = 0
        private const val MINUS_ONE = -1
        private const val ONE = 1
    }
}
