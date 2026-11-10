package com.example.shopsphere.data.model

data class ProductResponce(

    val products:List<Product>,
    val total:Int,
    val skip:Int,
    val limit: Int
)
