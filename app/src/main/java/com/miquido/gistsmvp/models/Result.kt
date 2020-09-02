package com.miquido.gistsmvp.models

sealed class Result<T> {
    class Success<T>(val value: T) : Result<T>()

    class Failure<T>(val exception: Throwable) : Result<T>()

    class Loading<T> : Result<T>()
}
