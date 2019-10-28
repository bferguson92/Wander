package com.example.wander.presenter

import com.example.wander.model.Favorite

class FavoritePresenter : FavoriteContract.Presenter {


    private val favorites = mutableListOf<Favorite>()

    override fun obtainFavorites(favorite: Favorite) {
        favorites.add(favorite)
    }

    override fun getFavorites() : List<Favorite> {
        return favorites
    }

}