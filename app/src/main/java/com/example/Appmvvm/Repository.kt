package com.example.Appmvvm

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class Repository {
    fun getResponseApi(context:Context, viewModel: MemeViewModel) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://meme-api.herokuapp.com/gimme/50"
        // Request a string response from the provided URL.

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val listMemes = mutableListOf<Meme>()
                val memes: JSONArray = it.getJSONArray("memes")
                val length: Int = memes.length()
                for (item in 0 until length) {
                    val itemJson: JSONObject = memes.getJSONObject(item)
                    val memeUrl = itemJson["url"].toString()
                    val memeTitle = itemJson["title"].toString()
                    val memeAuthor = itemJson["author"].toString()

                    val meme = Meme(memeAuthor, memeTitle, memeUrl)
                    listMemes.add(meme)
                }
                viewModel.setMemesList(listMemes)
            },
            {
                Log.d("no connection", "sin conexion")
        })
        // Add the request to the RequestQueue.
        queue.add(request)
    }
}