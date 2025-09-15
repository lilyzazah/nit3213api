package com.onrender.nit3213api.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Entity(
    val property1: String?,
    val property2: String?,
    val description: String?
) : Parcelable
