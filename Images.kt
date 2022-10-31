package com.app.myapp3.models.roomDatabase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageTable")
data class Images(

    @PrimaryKey
    @NonNull
    var id: String,

    @NonNull
    @ColumnInfo(name = "Image")
    var images: String,

)