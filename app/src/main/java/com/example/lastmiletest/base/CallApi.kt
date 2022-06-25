package com.example.lastmiletest.base

import com.example.lastmiletest.base.mappings.NetworkResult
import retrofit2.Response

suspend fun <T> callApi(block: suspend () -> Response<T>): NetworkResult<T> {
    val response = block()
    return if (response.isSuccessful) {
        response.body()?.let {
            NetworkResult.Success(it, response.code())
        } ?: NetworkResult.Complete(response.code())
    } else {
        NetworkResult.Error(response.message())
    }
}