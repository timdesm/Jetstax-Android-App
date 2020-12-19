package be.timdesmet.hogent_mobielplus_project.network.presistence.daos.shop

import androidx.lifecycle.LiveData
import androidx.room.*
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.BannerDBO

@Dao
interface BannerDAO {
    @Transaction
    @Query("SELECT * FROM banners ORDER BY id DESC LIMIT 1")
    fun getBanner() : LiveData<BannerDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanner(banner: BannerDBO)
}