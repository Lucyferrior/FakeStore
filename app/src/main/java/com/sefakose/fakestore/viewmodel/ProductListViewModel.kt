package com.sefakose.fakestore.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sefakose.fakestore.model.Product
import com.sefakose.fakestore.service.ProductAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel : ViewModel() {
    private val productAPI = ProductAPIService()
    val productData = MutableLiveData<ArrayList<Product>>()
    val productLoading = MutableLiveData<Boolean>(false)
    val productError = MutableLiveData<Boolean>(false)
    val isGrid = MutableLiveData<Boolean>(false)

    fun getProductsFromAPI() {
        productLoading.value = true
        productAPI.getAllProducts().enqueue(object : Callback<ArrayList<Product>> {
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                if(response.isSuccessful)
                    productData.value = response.body()
                productLoading.value = false
            }

            override fun onFailure(p0: Call<ArrayList<Product>>, p1: Throwable) {
                productLoading.value = false
                productError.value = true
                Log.e("Retrofit-Error", "Product loading error",p1 )
            }
        })

    }
}