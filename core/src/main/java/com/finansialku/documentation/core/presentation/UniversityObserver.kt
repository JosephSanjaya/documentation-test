/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import com.finansialku.documentation.core.domain.State
import com.finansialku.documentation.core.domain.University
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UniversityObserver(
    private val view: Interfaces,
    private val viewModel: UniversityViewModel,
    private val owner: LifecycleOwner
) :
    LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun addObserver() {
        owner.lifecycleScope.launch {
            viewModel.mFetch.collect {
                when (it) {
                    is State.Idle -> view.onUniversityFetchIdle()
                    is State.Loading -> view.onUniversityFetching()
                    is State.Success -> view.onUniversityFetchSuccess(it.data)
                    is State.Failed -> view.onUniversityFetchFailed(it.throwable)
                }
            }
        }
    }

    interface Interfaces {
        fun onUniversityFetchIdle() {}
        fun onUniversityFetching() {}
        fun onUniversityFetchSuccess(data: List<University?>) {}
        fun onUniversityFetchFailed(e: Throwable?) {}
    }
}
