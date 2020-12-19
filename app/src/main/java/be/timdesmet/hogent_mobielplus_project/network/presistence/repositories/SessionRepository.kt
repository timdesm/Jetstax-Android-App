package be.timdesmet.hogent_mobielplus_project.network.presistence.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import be.timdesmet.hogent_mobielplus_project.network.JetstaxApi
import be.timdesmet.hogent_mobielplus_project.network.UserEndpoint
import be.timdesmet.hogent_mobielplus_project.network.dtos.user.LoginDTO
import be.timdesmet.hogent_mobielplus_project.network.dtos.user.PasswordResetDTO
import be.timdesmet.hogent_mobielplus_project.network.dtos.user.ValidateDTO
import be.timdesmet.hogent_mobielplus_project.network.presistence.LocalDB
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user.SessionDBO
import be.timdesmet.hogent_mobielplus_project.utils.HashUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class SessionRepository(private val database: LocalDB) {
    val userId: LiveData<Int> = Transformations.map(database.sessionDAO.getSession()){it?.userId}
    val token: LiveData<String> = Transformations.map(database.sessionDAO.getSession()){it?.token}
    val email: LiveData<String> = Transformations.map(database.sessionDAO.getSession()){it?.email}

    suspend fun login(email: String, password: String) {
        withContext(Dispatchers.Main) {
            val service = JetstaxApi.buildApi(UserEndpoint::class.java)
            val response = service.login(LoginDTO(email, password, HashUtils.sha1(email + password)))
            print(response)
            if(response.isSuccessful) {
                val session = response.body()!!
                withContext(Dispatchers.IO) {
                    database.sessionDAO.insertSession(SessionDBO(session.userid, session.token, session.email))
                }
            }
            else {
                if(response.code() == 403) throw Exception("Invalid credentials provided")
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }

    suspend fun passwordReset(email: String) {
        withContext(Dispatchers.Main) {
            val service = JetstaxApi.buildApi(UserEndpoint::class.java)
            val response = service.passwordReset(PasswordResetDTO(email, HashUtils.sha1(email)))
            print(response)
            if(response.isSuccessful) {
                val body = response.body()!!
                return@withContext body.send
            }
            else {
                if(response.code() == 403) throw Exception("Invalid credentials provided")
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }

    suspend fun validate(userId: Int, token: String) {
        withContext(Dispatchers.Main) {
            val service = JetstaxApi.buildApi(UserEndpoint::class.java)
            val response = service.validate(ValidateDTO(userId, token))
            if(response.isSuccessful) {
                val validation = response.body()!!
                if(validation.valid) {

                }
            }
            else {
                if(response.code() == 403) throw Exception("Token rejected")
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) {
            database.sessionDAO.deleteSession()
        }
    }
}