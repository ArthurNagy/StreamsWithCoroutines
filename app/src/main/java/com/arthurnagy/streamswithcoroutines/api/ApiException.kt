package com.arthurnagy.streamswithcoroutines.api

data class ApiException(val errorCode: Int) : Exception()