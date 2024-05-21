package com.sefakose.fakestore.service

import com.sefakose.fakestore.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {

    @GET("products")
    //https://fakestoreapi.com/products
    fun getProducts(): Call<ArrayList<Product>>
}

