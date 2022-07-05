package com.app.android_clean_architecture_assignment.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
class CharacterLocal(
    @field:PrimaryKey val id: Long,
    @field:ColumnInfo(name = "name") val name: String?,
    @field:ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @field:ColumnInfo(name = "url") val url: String?,
)