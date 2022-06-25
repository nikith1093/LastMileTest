package com.example.lastmiletest.base.mappings


sealed class RepositoryResult<out T> {
    class Success<T>(val response: T) : RepositoryResult<T>()
    object Complete : RepositoryResult<Nothing>()
    data class Error(val message: String) : RepositoryResult<Nothing>()
}

fun <T> RepositoryResult<T>.toUseCaseResult(): UseCaseResult<T> {
    return when (this) {
        is RepositoryResult.Success -> {
            UseCaseResult.Success(response)
        }
        is RepositoryResult.Complete -> {
            UseCaseResult.Complete
        }
        is RepositoryResult.Error -> {
            UseCaseResult.Error
        }
    }
}
