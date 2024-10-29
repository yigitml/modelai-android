package com.foto.ai.util

sealed class Resource<T>(val data: T? = null, val message: String? = null, val error: Exception? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String, error: Exception, data: T? = null) :
        Resource<T>(data = data, message = message, error = error)

    class Loading<T>(data: T? = null) : Resource<T>(data = data)
}