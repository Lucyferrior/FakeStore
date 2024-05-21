package com.sefakose.fakestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    var rate: Double,
    var count: Int
): Parcelable