package be.timdesmet.hogent_mobielplus_project.network.presistence.daos.user

import androidx.lifecycle.LiveData
import androidx.room.*
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user.ClientDBO

@Dao
interface ClientDAO {
    @Transaction
    @Query("SELECT * FROM clients")
    fun getClients() : LiveData<List<ClientDBO>>

    @Transaction
    @Query("SELECT * FROM clients WHERE email = :email")
    fun getClientByEmail(email: String) : LiveData<ClientDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClient(client : ClientDBO)

    @Query("DELETE FROM clients")
    fun deleteClients()
}