package com.app.myapptest42.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.myapp3.models.roomDatabase.Images
import com.app.myapp3.models.roomDatabase.ImagesDao
import com.app.myapptest42.models.room.ImageDatabase
import com.app.myapptest42.utilities.Coroutines

class ImageViewModel(application: Application) : ViewModel() {

    var imagesDao: ImagesDao
    var myList: LiveData<Images>?

    init {
        val userDatabase: ImageDatabase = ImageDatabase.getDatabase(application)
        imagesDao = userDatabase.roomDao()
        myList = imagesDao.getImage("1")
    }

    fun insertImage(data: Images?) {
        Coroutines.main {
            imagesDao.saveImages(data!!)
        }
    }

    fun fetch(): LiveData<Images>? {
        return myList
    }

    fun updateImage(image: Images?) {

        Coroutines.main {
            imagesDao.updateImage(image!!)
        }
    }

}