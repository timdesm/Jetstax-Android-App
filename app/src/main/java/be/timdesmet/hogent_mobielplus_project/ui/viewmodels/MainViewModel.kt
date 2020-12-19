package be.timdesmet.hogent_mobielplus_project.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.SessionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val app: Application) : ViewModel() {

    private val viewModalJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModalJob + Dispatchers.Main)

    private val database by lazy { getDatabase(app) }
    private val sessionRepository by lazy { SessionRepository(database) }



    init {

    }

    fun logout() {
        viewModelScope.launch {
            try {
                sessionRepository.logout()
            }
            catch (e: Exception) {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModalJob.cancel()
    }
}