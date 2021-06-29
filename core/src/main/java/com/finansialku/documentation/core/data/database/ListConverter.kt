/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.data.database

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        return if (value.isNullOrBlank()) emptyList()
        else value.split(",").toList()
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        return list?.joinToString(",") ?: ""
    }
}
