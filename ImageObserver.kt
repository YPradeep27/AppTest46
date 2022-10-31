package com.app.myapptest42.view.activity

import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.app.myapp3.models.roomDatabase.Images
import com.app.myapptest42.viewmodels.ImageViewModel
import com.app.myapptest42.R
import com.app.myapptest42.databinding.ActivityMainBinding
import com.app.myapptest42.utilities.Constants.imageUrl
import com.app.myapptest42.utilities.createFactory
import com.app.myapptest42.utilities.isNetworkActiveWithMessage
import com.app.myapptest42.utilities.setImageGlide


class ImageObserver(private val imageActivity: ImageActivity) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){

        var count  = 0
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(imageActivity,
            R.layout.activity_main)
        val imageViewModel = ViewModelProvider(imageActivity,
            ImageViewModel(imageActivity.application).createFactory()).get(ImageViewModel::class.java)

        imageViewModel.fetch()?.observe(imageActivity, Observer { images ->

            images?.let {
                if (it.id.equals("1")){

                    imageActivity.setImageGlide(it.images,binding.imgView)

                }
            }
        })

        binding.btnGetImage.setOnClickListener {

            if(imageActivity.isNetworkActiveWithMessage()) {
                val position = (0..100).random()
                val image = Images("1","$imageUrl{$position}")

                imageActivity.setImageGlide("$imageUrl{$position}",binding.imgView)
                imageViewModel.fetch()?.observe(imageActivity, Observer {

                    if (it != null ){
                        count = it.id.toInt()
                    }
                })

                if (count == 0 ){
                    imageViewModel.insertImage(image)

                } else {
                    imageViewModel.updateImage(image)
                }

            }

        }

    }

}