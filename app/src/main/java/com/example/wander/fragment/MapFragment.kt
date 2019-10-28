package com.example.wander.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.wander.R
import com.example.wander.model.Favorite
import com.example.wander.view.MapsActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*

class MapFragment(private val listener: MapFragmentListener) : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val TAG = "MapFragment"

    interface MapFragmentListener {
        fun sendFavorites(favorite: Favorite)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = SupportMapFragment.newInstance()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d(TAG, "OnMapReady Called")
        map = googleMap

        val latitude = 42.249364
        val longitude = -88.950295
        val homeLatLng = LatLng(latitude, longitude)
        val zoom = 18f

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoom))
        map.addMarker(MarkerOptions().position(homeLatLng))
        setMapLongClick(map)
        setPoiClick(map)
        setMapStyle(map)

        val overlaySize = 100f
        val androidOverlay = GroundOverlayOptions()
            .image(BitmapDescriptorFactory.fromResource(R.drawable.android))
            .position(homeLatLng, overlaySize)

        map.addGroundOverlay(androidOverlay)
    }

     fun setMapStyle(map: GoogleMap) {
        try {
            // Customize the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.map_style
                )
            )

            if (!success) {
                Log.e("TAG", "Style parsing failed.")
            }
        }catch (e: Resources.NotFoundException) {
            Log.e("TAG", "Can't find style. Error: ", e)
        }
    }

     fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->

            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )

            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )

            listener.sendFavorites(Favorite(latLng.latitude, latLng.longitude))
            //favoritePresenter.obtainFavorites(Favorite(latLng.latitude, latLng.longitude))
        }
    }

     fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )

            poiMarker.showInfoWindow()

        }
    }


}