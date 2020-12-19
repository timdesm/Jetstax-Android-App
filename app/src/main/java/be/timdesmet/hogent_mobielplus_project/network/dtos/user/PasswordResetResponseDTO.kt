package be.timdesmet.hogent_mobielplus_project.network.dtos.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PasswordResetResponseDTO(
    @Json(name = "send") val send: Boolean,
    @Json(name = "email") val email: String)