package com.app.myapp3.models.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ImagesDao {

    @Insert
    suspend fun saveImages(images: Images)

    @Query("SELECT * FROM imageTable WHERE id=:images")
    fun getImage(images: String) : LiveData<Images>?

    @Update
    suspend fun updateImage(images: Images)

}