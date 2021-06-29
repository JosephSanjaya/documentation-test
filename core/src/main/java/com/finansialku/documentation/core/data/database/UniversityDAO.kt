/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.finansialku.documentation.core.domain.University

@Dao
interface UniversityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(universities: List<University>)

    @Query("SELECT * FROM `university`")
    fun get(): List<University>

}