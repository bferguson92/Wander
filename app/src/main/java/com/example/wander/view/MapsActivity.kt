package com.example.wander.view

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wander.R
import com.example.wander.fragment.FavoritesFragment
import com.example.wander.fragment.MapFragment
import com.example.wander.model.Favorite
import com.example.wander.presenter.FavoritePresenter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*

class MapsActivity : AppCompatActivity(),  MapFragment.MapFragmentListener {

    private val favoritePresenter = FavoritePresenter()
    private val mapFragment = MapFragment(this)
    private val favoritesFragment = FavoritesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_main, mapFragment).commit()

        bottom_nav_maps.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.map_page -> {
                    Log.d("NAV_BAR", "GO TO MAP")
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout_main, mapFragment).commit()
                    true
                }
                R.id.favorites_page -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout_main, favoritesFragment).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
//        // Change the map type based on the user's selection.
//        R.id.normal_map -> {
//            map.mapType = GoogleMap.MAP_TYPE_NORMAL
//            true
//        }
//        R.id.hybrid_map -> {
//            map.mapType = GoogleMap.MAP_TYPE_HYBRID
//            true
//        }
//        R.id.satellite_map -> {
//            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
//            true
//        }
//        R.id.terrain_map -> {
//            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
//            true
//        }
//        else -> super.onOptionsItemSelected(item)
//    }


    override fun sendFavorites(favorite: Favorite) {
        favoritePresenter.obtainFavorites(favorite)
    }
}
