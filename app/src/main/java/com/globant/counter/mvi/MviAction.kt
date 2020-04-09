package com.globant.counter.mvi

/**
 * Represent and Action taken by the user in the MVI example view
 */
sealed class MviAction {
    object IncrementAction : MviAction()
    object DecrementAction : MviAction()
    object ResetAction : MviAction()
}
