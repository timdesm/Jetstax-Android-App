package be.timdesmet.hogent_mobielplus_project.services

import androidx.lifecycle.LiveData
import be.timdesmet.hogent_mobielplus_project.domain.user.Client

class UserService {
    companion object {
        lateinit var userId: LiveData<Int>
        lateinit var email: LiveData<String>
        lateinit var token: LiveData<String>
        lateinit var client: LiveData<Client>
        val loggedIn get() = !token.value.isNullOrBlank()
    }
}