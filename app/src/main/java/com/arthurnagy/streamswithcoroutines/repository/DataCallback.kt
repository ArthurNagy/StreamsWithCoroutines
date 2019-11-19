package com.arthurnagy.streamswithcoroutines.repository

interface DataCallback<T> {
    fun onResult(result: T)
    fun onError(throwable: Throwable)
}