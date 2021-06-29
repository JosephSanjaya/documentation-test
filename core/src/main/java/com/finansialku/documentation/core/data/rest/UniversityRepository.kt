/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.data.rest

import android.annotation.SuppressLint
import com.blankj.utilcode.util.NetworkUtils
import com.finansialku.documentation.core.data.database.UniversityDAO
import com.finansialku.documentation.core.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UniversityRepository(
    val service: ApiInterfaces,
    val dao: UniversityDAO
) {
    @SuppressLint("MissingPermission")
    fun fetchUniversities(
        country: String
    ) = flow {
        emit(State.Loading())
        val result = if (NetworkUtils.isConnected()) {
            val data = service.getUniversitiesByCountry(
                country
            )
            dao.insert(data?.filterNotNull() ?: emptyList())
            dao.get()
        } else {
            dao.get()
        }
        emit(State.Success(result))
    }.flowOn(Dispatchers.IO)
}
