package com.example.wander.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wander.R
import com.example.wander.adapter.FavoriteAdapter
import com.example.wander.model.Favorite
import com.example.wander.presenter.FavoritePresenter
import com.example.wander.presenter.FavoriteContract
import kotlinx.android.synthetic.main.favorites_list.*

class FavoritesFragment : Fragment(), FavoriteContract.View {


    private lateinit var presenter: FavoritePresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FavoritePresenter()
        displayFavorites(presenter.getFavorites())
    }

    override fun displayFavorites(favorites: List<Favorite>) {
        recycler_view_favorites.adapter = FavoriteAdapter(favorites)
        recycler_view_favorites.layoutManager = LinearLayoutManager(context)
    }
}