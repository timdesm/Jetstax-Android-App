package be.timdesmet.hogent_mobielplus_project.network.presistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.daos.user.SessionDAO
import be.timdesmet.hogent_mobielplus_project.network.presistence.daos.shop.BannerDAO
import be.timdesmet.hogent_mobielplus_project.network.presistence.daos.shop.ProductDAO
import be.timdesmet.hogent_mobielplus_project.network.presistence.daos.shop.ProductGroupDAO
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.user.SessionDBO
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.BannerDBO
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.ProductGroupDBO
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product.ProductDBO

@Database(entities = [
    SessionDBO::class,
    BannerDBO::class,
    ProductDBO::class,
    ProductGroupDBO::class
], version = 1, exportSchema = false)
abstract class LocalDB: RoomDatabase() {
    abstract val sessionDAO: SessionDAO
    abstract val bannerDAO: BannerDAO
    abstract val productDAO: ProductDAO
    abstract val productGroupDAO: ProductGroupDAO
}

private lateinit var INSTANCE: LocalDB

fun getDatabase(context: Context): LocalDB {
    synchronized(LocalDB::class.java) {
        if(!::INSTANCE.isInitialized)
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            LocalDB::class.java, "database").build()
    }
    return INSTANCE
}