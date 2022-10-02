package com.example.Appmvvm

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InfoMeme : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_info_meme, container, false)

        val url = arguments?.getString("url")
        val title = arguments?.getString("title")
        val memeview = rootview.findViewById<ImageView>(R.id.memeView)
        rootview.findViewById<TextView>(R.id.textoTitleMeme).text = title
        Glide.with(container!!.context).load(url).into(memeview)
        rootview.findViewById<FloatingActionButton>(R.id.botonDescargar).setOnClickListener {
            imageDownload(url!!)
        }
        return rootview
    }

    private fun imageDownload(url: String) {
        val downloadManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse(url)

        val request = DownloadManager.Request(uri)
        request.setTitle("Meme")
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        downloadManager.enqueue(request)
    }
}