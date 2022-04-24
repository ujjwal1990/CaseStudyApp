package com.kc.casestudyapp.data.utils

sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(error: String, data: T? = null) : Resource<T>(data, error)
}
