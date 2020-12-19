package be.timdesmet.hogent_mobielplus_project.services

import androidx.lifecycle.LiveData

class UserService {
    companion object {
        lateinit var userId: LiveData<Int>
        lateinit var token: LiveData<String>
        val loggedIn get() = !token.value.isNullOrBlank()
    }
}