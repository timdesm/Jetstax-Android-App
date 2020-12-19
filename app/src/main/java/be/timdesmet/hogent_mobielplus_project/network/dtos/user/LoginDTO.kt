package be.timdesmet.hogent_mobielplus_project.network.dtos.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginDTO (
    val email: String,
    val password: String,
    val hash: String
) : Parcelable