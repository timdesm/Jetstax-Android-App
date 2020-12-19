package be.timdesmet.hogent_mobielplus_project.network.presistence.daos.shop

import androidx.lifecycle.LiveData
import androidx.room.*
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.GroupWithProducts
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.ProductGroupDBO
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product.ProductDBO

@Dao
interface ProductGroupDAO {
    @Transaction
    @Query("SELECT * FROM product_groups ORDER BY weight ASC")
    fun getProductGroups() : LiveData<List<ProductGroupDBO>>

    @Transaction
    @Query("SELECT * FROM product_groups WHERE id = :id")
    fun getProductGroup(id: Int) : LiveData<ProductGroupDBO>

    //@Transaction
    //@Query("SELECT * FROM product_groups INNER JOIN products ON product_groups.id = products.gid WHERE product_groups.id = :id LIMIT 1")
    //fun getProductGroupWithProducts(id: Int) : LiveData<GroupWithProducts>

    @Transaction
    @Query("SELECT * FROM products WHERE gid = :gid")
    fun getGroupProducts(gid: Int) : LiveData<List<ProductDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(productGroup: ProductGroupDBO)
}