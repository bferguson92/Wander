package com.example.wander.presenter

import com.example.wander.model.Favorite

interface FavoriteContract {
    interface Presenter{
        fun getFavorites() : List<Favorite>
        fun obtainFavorites(favorite: Favorite)
    }

    interface View {
        fun displayFavorites( favorites: List<Favorite>)
    }
}