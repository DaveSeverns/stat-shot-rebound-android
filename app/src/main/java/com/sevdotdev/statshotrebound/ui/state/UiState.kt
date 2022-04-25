package com.sevdotdev.statshotrebound.ui.state

sealed class ComposeState<T>(val payload: T? = null) {
    class Ready<T>(payload: T?) : ComposeState<T>(payload = payload)
    class Loading<T> : ComposeState<T>()
    class Error<T>(
        payload: T? = null,
        val errorMessage: String? = null,
        val throwable: Throwable? = null
    ) : ComposeState<T>(payload = payload)
}