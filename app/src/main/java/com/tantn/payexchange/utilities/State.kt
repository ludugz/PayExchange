package com.tantn.payexchange.utilities

sealed class State<T> {
    class Loading<T> : State<T>()
    class Success<T>(val data: T) : State<T>()
    class Failure<T>(val message: String) : State<T>()
    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failure(message: String) = Failure<T>(message)
    }
}