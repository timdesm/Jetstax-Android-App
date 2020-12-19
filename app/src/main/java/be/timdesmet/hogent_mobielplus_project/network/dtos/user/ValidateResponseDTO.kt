package be.timdesmet.hogent_mobielplus_project.network.dtos.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValidateResponseDTO(
    @Json(name = "valid") val valid: Boolean,
    @Json(name = "token") val token: String,
    @Json(name = "userid") val userid: Int)