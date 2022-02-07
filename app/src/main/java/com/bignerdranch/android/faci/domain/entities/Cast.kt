package com.bignerdranch.android.faci.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    val id: Int,
    val name: String,
    val profile_path: String?
): Parcelable