package be.timdesmet.hogent_mobielplus_project.network.dtos.user

import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user.SessionDBO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDTO(
    @Json(name = "userid") val userid: Int,
    @Json(name = "token") val token: String,
    @Json(name = "email") val email: String) {

    fun toDBO() : SessionDBO {
        return SessionDBO(userid, token, email)
    }
}