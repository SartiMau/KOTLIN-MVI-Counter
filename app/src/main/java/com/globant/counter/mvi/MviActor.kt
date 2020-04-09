package com.globant.counter.mvi

/**
 * Actor responsible for passing user actions to a consuming function.
 *
 * @param emit A [Unit] that emits an [MviAction] to a receiver
 *
 * Note: We pass a unit rather than the ViewModel, this helps to better separate logic. The XML doesn't need the
 * ViewModel, only something to handle state changes
 */
class MviActor(private val emit: (MviAction) -> Unit) {
    fun incrementClicked() = emit(MviAction.IncrementAction)
    fun decrementClicked() = emit(MviAction.DecrementAction)
    fun resetClicked() = emit(MviAction.ResetAction)
}
