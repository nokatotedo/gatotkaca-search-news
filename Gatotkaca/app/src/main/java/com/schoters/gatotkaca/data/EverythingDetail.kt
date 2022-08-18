package com.schoters.gatotkaca.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EverythingDetail(
    val title: String,
    val description: String
): Parcelable