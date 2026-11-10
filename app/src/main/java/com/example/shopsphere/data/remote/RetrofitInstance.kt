package com.example.shopsphere.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

 private const val BASE_URL = "https://dummyjson.com/"

 fun getInstance(): Retrofit{

  return Retrofit.Builder().baseUrl(BASE_URL)
   .addConverterFactory(GsonConverterFactory.create())
   .build()
 }

 fun getApi(): ProductApi{

  return getInstance().create(ProductApi::class.java)
 }
}