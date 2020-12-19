package be.timdesmet.hogent_mobielplus_project.ui.viewmodels.auth

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.SessionRepository
import be.timdesmet.hogent_mobielplus_project.services.UserService
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class PasswordViewModel(app: Application)  : ViewModel() {

    private val app = app

    // Database connection
    private val viewModelJob = SupervisorJob()
    private val database = getDatabase(app)
    private val sessionRepository = SessionRepository(database)

    val email = MutableLiveData<String>()

    init {
        if(UserService.loggedIn) {
            viewModelScope.launch {
                sessionRepository.logout()
            }
        }
    }

    fun reset() {
        try {
            if(email.value.isNullOrBlank() || !email.value!!.matches(Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")))
            { throw IllegalArgumentException("Invalid email provided") }
            resetRequest()
        } catch(e: Exception) {
            Toast.makeText(app, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun resetRequest() {
        viewModelScope.launch {
            try {
                sessionRepository.passwordReset(email.value!!)
                Toast.makeText(app, "Email has been send", Toast.LENGTH_LONG).show()
                email.value = ""
            } catch(e: Exception) {
                e.printStackTrace()
                Toast.makeText(app, "${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}