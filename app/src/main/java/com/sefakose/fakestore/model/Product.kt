package com.sefakose.fakestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    var id: Int,
    var title: String,
    var price: Double,
    var description: String,
    var image: String,
    var rating: Rating): Parcelable

