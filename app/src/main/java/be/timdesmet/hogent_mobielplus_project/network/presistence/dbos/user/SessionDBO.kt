package be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SessionDBO(
    @PrimaryKey
    val userId: Int,
    val token: String,
    val email: String
)