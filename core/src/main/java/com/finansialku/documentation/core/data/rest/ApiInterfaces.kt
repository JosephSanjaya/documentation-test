/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.data.rest

import android.content.Context
import com.finansialku.documentation.core.domain.University
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaces {

    companion object {
        operator fun invoke(mContext: Context): ApiInterfaces {
            val baseService =
                ApiServices(
                    mContext
                )
            return baseService.retrofit.create(ApiInterfaces::class.java)
        }
    }

    @GET("search")
    suspend fun getUniversitiesByCountry(
        @Query("country") country: String = "Indonesia"
    ): List<University?>?
}
