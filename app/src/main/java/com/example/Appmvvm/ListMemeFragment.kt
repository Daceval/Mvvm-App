package com.example.Appmvvm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListMemeFragment : Fragment() {
    private val viewModel: MemeViewModel by viewModels()
    @SuppressLint("NotifyDataSetChanged")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_list_meme, container, false)
        val recyclerMeme = rootview.findViewById<RecyclerView>(R.id.recyclerMeme)
        recyclerMeme.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val adapter = MemeAdapter(container!!.context)
        recyclerMeme.adapter = adapter

        viewModel.getMemesList().observe(this.viewLifecycleOwner, Observer {
            adapter.setMemeList(it)
            adapter.notifyDataSetChanged()
        })
        rootview.findViewById<FloatingActionButton>(R.id.botonMeme).setOnClickListener {
            viewModel.fetchMemes(container.context)
        }

        adapter.memeSelected {url, title ->
            val bundle = Bundle()
            bundle.putString("url", url)
            bundle.putString("title", title)
            findNavController().navigate(R.id.action_listMemeFragment_to_infoMeme, bundle)
        }
        return rootview
    }

}