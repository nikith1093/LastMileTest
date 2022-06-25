package com.example.lastmiletest.base.mappings

sealed class UseCaseResult<out T> {
    class Success<T>(val data: T) : UseCaseResult<T>()
    object Complete : UseCaseResult<Nothing>()
    object Error : UseCaseResult<Nothing>()
}