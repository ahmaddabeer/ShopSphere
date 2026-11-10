package com.example.shopsphere.data.remote

import com.example.shopsphere.data.model.Product
import com.example.shopsphere.data.model.ProductResponce
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {


    @GET("products")
    suspend fun getProductData(): ProductResponce

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id:Int
    ): Product



    @GET("products/search")
    suspend fun searchProducts(
        @Query("q")  query: String

    ): ProductResponce

    @GET("products/category/{category}")
    suspend fun getProductByCategory(

        @Path("category") category:String
    ): ProductResponce

}