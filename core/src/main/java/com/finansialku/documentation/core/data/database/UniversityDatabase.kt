/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.finansialku.documentation.core.domain.University

@Database(
    entities = [
        University::class,
    ],
    version = 1, exportSchema = false
)
@TypeConverters(ListConverter::class)

abstract class UniversityDatabase : RoomDatabase() {
    abstract fun getUniversity(): UniversityDAO

    companion object {
        const val DB_NAME = "university.db"
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }
    }
}
