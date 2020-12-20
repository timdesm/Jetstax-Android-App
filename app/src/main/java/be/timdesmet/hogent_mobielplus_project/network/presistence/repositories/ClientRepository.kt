package be.timdesmet.hogent_mobielplus_project.network.presistence.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import be.timdesmet.hogent_mobielplus_project.domain.user.Client
import be.timdesmet.hogent_mobielplus_project.network.JetstaxApi
import be.timdesmet.hogent_mobielplus_project.network.UserEndpoint
import be.timdesmet.hogent_mobielplus_project.network.presistence.LocalDB
import be.timdesmet.hogent_mobielplus_project.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ClientRepository(private val database: LocalDB) {



    suspend fun refreshCurrentClient(email: String) {
        if(!UserService.loggedIn)
            throw SecurityException()

        withContext(Dispatchers.IO) {
            val response = JetstaxApi.buildApi(UserEndpoint::class.java).getClientDetails(email, "${UserService.token.value}")
            if(response.isSuccessful) {
                val client = response.body()
                if (client != null) {
                    database.clientDAO.insertClient(client.toDBO())
                }
            }
            else {
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }

    fun getClientByEmail(email: String) : LiveData<Client> {
        return Transformations.map(database.clientDAO.getClientByEmail(email)) {it?.toDomain()}
    }

}