package be.timdesmet.hogent_mobielplus_project.network.presistence.daos.shop

import androidx.lifecycle.LiveData
import androidx.room.*
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product.ProductDBO

@Dao
interface ProductDAO {
    @Transaction
    @Query("SELECT * FROM products")
    fun getProducts() : LiveData<List<ProductDBO>>

    @Transaction
    @Query("SELECT * FROM products WHERE gid = :gid")
    fun getGroupProducts(gid: Int) : LiveData<List<ProductDBO>>

    @Transaction
    @Query("SELECT * FROM products WHERE pid = :id")
    fun getProduct(id: Int) : LiveData<ProductDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductDBO)
}