package com.sefakose.fakestore.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.sefakose.fakestore.R
import com.sefakose.fakestore.databinding.FragmentProductDetailsBinding
import com.sefakose.fakestore.model.Product
import com.sefakose.fakestore.util.downloadURL

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
        val product: Product = args.product

        product.apply {
            with(binding) {
                tvDetailTitle.text = title
                tvDetailDescription.text = description
                tvDetailPrice.text = price.toString()
                imageView.downloadURL(image)
                ratingBar.rating = rating.rate.toFloat()
                tvRatingCount.text = rating.rate.toString()
            }
        }
    }
}