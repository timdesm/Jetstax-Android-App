package be.timdesmet.hogent_mobielplus_project.network.dtos.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValidateDTO (
    val userid: Int,
    val token: String,
) : Parcelable