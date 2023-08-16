package ru.com.bulat.notesmvvm.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.com.bulat.notesmvvm.R
import ru.com.bulat.notesmvvm.model.AppNote

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var mListNotes = emptyList<AppNote>()

    class MainHolder (view : View) : RecyclerView.ViewHolder(view) {
        val nameNote : TextView = view.findViewById(R.id.item_note_name)
        val textNote : TextView = view.findViewById(R.id.item_note_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int = mListNotes.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mListNotes[position].name
        holder.textNote.text = mListNotes[position].text
    }

    fun setList(list: List<AppNote>){
        mListNotes = list
        notifyDataSetChanged()
    }
}