package com.example.Appmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import de.hdodenhof.circleimageview.CircleImageView

class MemeAdapter(private val context: Context): RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {
    private var memeList = mutableListOf<Meme>()

   private var selected: ((String, String)->Unit)? = null

    fun memeSelected(listener: (String, String)->Unit) {
        selected = listener
    }

    fun setMemeList(list: MutableList<Meme>) {
        memeList = list
    }
    class MemeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(meme: Meme, context: Context) {
            val image = itemView.findViewById<CircleImageView>(R.id.memeImage)
            itemView.findViewById<TextView>(R.id.authorText).text = meme.author
            itemView.findViewById<TextView>(R.id.title_text).text = meme.title
            Glide.with(context).load(meme.url).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val memeLayout = inflater.inflate(R.layout.view_holder_meme, parent, false)
        return MemeViewHolder(memeLayout)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memeList[position]
        holder.bind(meme, context)
        holder.itemView.setOnClickListener {
            selected?.let {
                it(meme.url, meme.title)
            }
        }
    }

    override fun getItemCount(): Int {
        return memeList.size
    }
}