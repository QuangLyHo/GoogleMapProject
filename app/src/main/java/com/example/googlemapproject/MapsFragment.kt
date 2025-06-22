package com.example.googlemapproject

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.googlemapproject.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class MapsFragment : Fragment() {
    private var passedAddress: String? = null
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passedAddress = arguments?.getString("address")
    }

    private val callback = OnMapReadyCallback { map ->
        googleMap = map
        updateMap()
    }

    private fun updateMap() {
        val address = passedAddress ?: return

        val latlon = getLatLongFromAddress(requireContext(), address)
        if (latlon != null) {
            googleMap?.clear()
            googleMap?.addMarker(MarkerOptions().position(latlon).title("Marker at $address"))
            googleMap?.setMinZoomPreference(17.0f)
            googleMap?.moveCamera(CameraUpdateFactory.newLatLng(latlon))
        }
        else {
            Toast.makeText(requireContext(), "Address is not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun getLatLongFromAddress(context: Context, address:String): LatLng? {
        val geocoder = Geocoder(context, Locale.getDefault())
        return try {

            val addresses: List<Address> = geocoder.getFromLocationName(address, 1)!!
            if (addresses.isNotEmpty()) {
                val location: Address = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        
    }

    //create new instance to pass data to fragment
    //enables multiple map searches
    //instead of reusing the same fragment each search
    companion object {
        fun newInstance(address: String) : MapsFragment {
            val fragment = MapsFragment()
            val bundle = Bundle()
            bundle.putString("address", address)
            fragment.arguments = bundle
            return fragment
        }
    }
}