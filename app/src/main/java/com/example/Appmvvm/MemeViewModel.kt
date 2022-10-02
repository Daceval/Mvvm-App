package com.example.Appmvvm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MemeViewModel: ViewModel() {
    private var listMemes = MutableLiveData<MutableList<Meme>>()

    fun getMemesList(): MutableLiveData<MutableList<Meme>> {
        return listMemes
    }

    fun setMemesList(list: MutableList<Meme>) {
        listMemes.postValue(list)
    }

    fun fetchMemes(context: Context) {
        val rep = Repository()
        rep.getResponseApi(context, this)
    }
}