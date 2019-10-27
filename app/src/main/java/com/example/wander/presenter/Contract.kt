package com.example.wander.presenter

interface Contract {
    interface Presenter {
        fun getMapType(): Int
    }

    interface View {
        fun displayMapType(mapType: Int)
    }
}