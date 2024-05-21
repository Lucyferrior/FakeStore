package com.sefakose.fakestore.service

import com.sefakose.fakestore.model.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPIService {
    private val BASE_URL = "https://fakestoreapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductAPI::class.java)

    fun getAllProducts(): Call<ArrayList<Product>> {
        return api.getProducts()
    }
}