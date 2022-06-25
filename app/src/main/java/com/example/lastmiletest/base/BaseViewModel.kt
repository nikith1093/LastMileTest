package com.example.lastmiletest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.virginmoneytest.base.SingleLiveEvent
import com.virginmoneytest.base.ViewModelIntent
import com.virginmoneytest.base.ViewModelState

abstract class BaseViewModel<I : ViewModelIntent, S : ViewModelState> : ViewModel() {
    abstract fun sendIntent(intent: I)

    protected val state = SingleLiveEvent<S>()

    fun getState(): LiveData<S> {
        return state
    }
}