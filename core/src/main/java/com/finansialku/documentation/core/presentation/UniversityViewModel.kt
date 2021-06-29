/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.presentation

import com.finansialku.documentation.core.data.rest.UniversityRepository
import com.finansialku.documentation.core.domain.State
import com.finansialku.documentation.core.domain.University
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UniversityViewModel(
    val repo: UniversityRepository
) : BaseViewModel() {

    private val _fetch = MutableStateFlow<State<List<University>>>(
        State.Idle()
    )
    val mFetch: StateFlow<State<List<University>>> get() = _fetch

    fun fetchUniversities(
        country: String
    ) = ioScope.launch {
        repo.fetchUniversities(
            country
        )
            .catch {
                _fetch.emit(State.Failed(it))
            }
            .collect {
                _fetch.emit(it)
            }
    }
}
