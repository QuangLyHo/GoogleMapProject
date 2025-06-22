package com.example.googlemapproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UniversityAdapter(
    private val universityList: List<University>
) : RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    class UniversityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.universityName)
        val addressTextView: TextView = view.findViewById(R.id.universityAddress)
    }

    //inflates item_university.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_university, parent, false)
        return UniversityViewHolder(view)
    }

    //binds the data from data class to Textview
    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val university = universityList[position]
        holder.nameTextView.text = university.name
        holder.addressTextView.text = university.address
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return universityList.size
    }
}