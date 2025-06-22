package com.example.googlemapproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UniversityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.universityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val universityList = listOf(
            University("BMCC", "199 Chambers St"),
            University("Baruch", "55 Lexington Ave"),
            University("Hunter College", "695 Park Ave"),
            University("John Jay Community College", "524 W 59th St")
        )

        adapter = UniversityAdapter(universityList)
        recyclerView.adapter = adapter

        return view
    }
}