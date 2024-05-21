package com.sefakose.fakestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sefakose.fakestore.R
import com.sefakose.fakestore.databinding.FragmentProductDetailsBinding
import com.sefakose.fakestore.model.Product
import com.sefakose.fakestore.model.Rating

class ProductDetailsFragment : Fragment() {
    private val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_product_details, container, false
        )
        initUI()
        return binding.root
    }

    private fun initUI() {
        val product : Product = args.product

        product.apply {
            binding.tvDetailTitle.text = title
            binding.tvDetailDescription.text = description
            binding.tvDetailPrice.text = price.toString()
            Glide.with(this@ProductDetailsFragment).load(image).into(binding.imageView)
            binding.ratingBar.rating = rating.rate.toFloat()
            binding.tvRatingCount.text = rating.rate.toString()
        }
    }
}