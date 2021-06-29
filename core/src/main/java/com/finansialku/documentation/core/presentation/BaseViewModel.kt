/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finansialku.documentation.core.domain.State
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Logger.e(throwable, "Coroutines Error")
    }
    protected val mainScope = CoroutineScope(Dispatchers.Main + exceptionHandler)
    protected val ioScope = CoroutineScope(Dispatchers.IO + exceptionHandler)
    protected val defaultScope = CoroutineScope(viewModelScope.coroutineContext + exceptionHandler)

    override fun onCleared() {
        super.onCleared()
        defaultScope.coroutineContext.cancel()
        mainScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }

    fun <T> MutableStateFlow<State<T>>.resetState() {
        value = State.Idle()
    }
}
