package be.timdesmet.hogent_mobielplus_project.network

import be.timdesmet.hogent_mobielplus_project.network.dtos.shop.BannerDTO
import be.timdesmet.hogent_mobielplus_project.network.dtos.shop.ProductDTO
import be.timdesmet.hogent_mobielplus_project.network.dtos.shop.ProductGroupDTO
import be.timdesmet.hogent_mobielplus_project.network.dtos.user.*
import be.timdesmet.hogent_mobielplus_project.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.lang.reflect.Type

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val nullOnEmptyConverterFactory = object : Converter.Factory() {
    fun converterFactory() = this
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
        val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
        override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
    }
}

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.API_BASE_URl)
    .addConverterFactory(nullOnEmptyConverterFactory)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

object JetstaxApi {
    fun<T> buildApi(service: Class<T>): T{
        return retrofit.create(service)
    }
}

interface UserEndpoint {
    @POST("app/user/login")
    suspend fun login(@Body loginDTO: LoginDTO) : Response<LoginResponseDTO>

    @POST("app/user/password")
    suspend fun passwordReset(@Body passwordResetDTO: PasswordResetDTO) : Response<PasswordResetResponseDTO>

    @POST("app/user/validate")
    suspend fun validate(@Body validateDTO: ValidateDTO) : Response<ValidateResponseDTO>

    @GET("app/user/details/{email}")
    suspend fun getDetails(@Body validateDTO: ValidateDTO) : Response<ValidateResponseDTO>
}

interface ShopEndpoint {
    // Get a list fo all products
    @GET("app/shop/products")
    suspend fun getProducts() : Response<List<ProductDTO>>

    // Get store ad banner
    @GET("app/shop/banner")
    suspend fun getBanner() : Response<BannerDTO>

    // Get a list of all product groups
    @GET("app/shop/groups")
    suspend fun getProductGroups() : Response<List<ProductGroupDTO>>

    // Get a list of all products of a group
    @GET("app/shop/groups/{group_id}/products")
    suspend fun getGroupProducts(@Path(value = "group_id", encoded = true) groupId: Int) : Response<List<ProductDTO>>
}