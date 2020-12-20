package be.timdesmet.hogent_mobielplus_project.network.presistence.daos.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user.SessionDBO

@Dao
interface SessionDAO {
    @Transaction
    @Query("SELECT * FROM SessionDBO LIMIT 1")
    fun getSession() : LiveData<SessionDBO>

    @Insert
    fun insertSession(token : SessionDBO)

    @Query("DELETE FROM SessionDBO")
    fun deleteSession()
}