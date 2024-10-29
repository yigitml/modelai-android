package com.foto.ai.util

sealed class ObjectState<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ObjectState<T>(data = data)
    class Error<T>(message: String, error: Exception, data: T? = null) :
        ObjectState<T>(data = data, message = message)

    class Loading<T>(data: T? = null) : ObjectState<T>(data = data)
}