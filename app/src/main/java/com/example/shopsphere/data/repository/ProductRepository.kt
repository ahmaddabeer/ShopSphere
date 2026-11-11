package com.example.shopsphere.data.repository

import com.example.shopsphere.data.model.Product
import com.example.shopsphere.data.remote.ProductApi
import javax.inject.Inject


class ProductRepository @Inject constructor(private val api: ProductApi){




    suspend fun getProduct() = api.getProductData()
    suspend fun getProductById(id: Int) = api.getProductById(id)
    suspend fun  searchProducts(query:String) =
        api.searchProducts(query)


    suspend fun getProductByCategory(category: String) =
        api.getProductByCategory(category)

}