/*
 * Copyright (c) 2021. Designed and developed by Joseph Sanjaya, S.T., M.Kom., All Rights Reserved.
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */

package com.finansialku.documentation.core.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "university",
    indices = [Index(value = ["name"], unique = true)]
)
data class University(
    @Transient
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @field:SerializedName("country")
    @ColumnInfo(name = "country")
    val country: String? = null,

    @field:SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String? = null,

    @field:SerializedName("state-province")
    @ColumnInfo(name = "state-province")
    val stateProvince: String? = null,

    @field:SerializedName("alpha_two_code")
    @ColumnInfo(name = "alpha_two_code")
    val alphaTwoCode: String? = null,

    @field:SerializedName("web_pages")
    @ColumnInfo(name = "web_pages")
    val webPages: List<String?>? = null,

    @field:SerializedName("domains")
    @ColumnInfo(name = "domains")
    val domains: List<String?>? = null,
)
