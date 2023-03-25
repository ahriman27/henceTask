package com.example.hencetest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hencetest.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment(var list : ArrayList<MarkerModel>) : Fragment(), OnMapReadyCallback,Notify {

    lateinit var binding : FragmentMapBinding
    lateinit var googleMap : GoogleMap
    lateinit var ctx : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mapInter = this
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_map, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val mapFragment = childFragmentManager
                .findFragmentById(R.id.google_map) as SupportMapFragment
            mapFragment.getMapAsync(this@MapFragment)
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {

        googleMap.setOnMapClickListener(OnMapClickListener { latLng ->
            val markerOptions = MarkerOptions()

            markerOptions.position(latLng)

            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)

//            googleMap.clear()

            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))

            val marker = googleMap.addMarker(markerOptions)
            (ctx as MainActivity).markerList.add(marker)
            val model = MarkerModel(latLng.latitude.toString() + " : " + latLng.longitude,latLng)
            list.add(model)

        })

    }

    override fun onEvent(pos : Int) {
        (ctx as MainActivity).markerList[pos]?.remove()
        (ctx as MainActivity).markerList.removeAt(pos)
    }

}