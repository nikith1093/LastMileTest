package com.example.lastmiletest.base.mappings


fun <T> NetworkResult<T>.toRepositoryResult(): RepositoryResult<T> {
    return when (this) {
        is NetworkResult.Success -> RepositoryResult.Success(this.response)
        is NetworkResult.Complete -> RepositoryResult.Complete
        is NetworkResult.Error -> RepositoryResult.Error(this.message)
    }
}

sealed class NetworkResult<out T> {
    class Success<T>(val response: T, val responseCode: Int) : NetworkResult<T>()
    class Complete(val responseCode: Int) : NetworkResult<Nothing>()
    class Error(val message: String) : NetworkResult<Nothing>()
}
