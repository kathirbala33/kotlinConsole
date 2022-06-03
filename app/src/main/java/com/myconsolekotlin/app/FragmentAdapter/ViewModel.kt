package com.myconsolekotlin.app.FragmentAdapter

import android.app.Application
import android.graphics.ColorSpace.Model
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class ViewModel(application: Application) : AndroidViewModel(application) {

    private var modelMutableLiveData: MutableLiveData<Model>? = null

}