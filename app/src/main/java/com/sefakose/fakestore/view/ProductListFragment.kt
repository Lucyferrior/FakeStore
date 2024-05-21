package com.sefakose.fakestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sefakose.fakestore.R
import com.sefakose.fakestore.adapter.ProductListAdapter
import com.sefakose.fakestore.databinding.FragmentProductListBinding
import com.sefakose.fakestore.viewmodel.ProductListViewModel

class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        //viewmodel onCreate aşamasında çağırdım çünkü onCreateView her çalıştığında yeni bir tane oluşturulursa viewmodelin anlamı kalmaz
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        viewModel.getProductsFromAPI()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_list, container, false
        )
        binding.rvProductList.layoutManager = LinearLayoutManager(context)
        showToast()
        setObservers()
        return binding.root
    }

    private fun showToast() {
        Toast.makeText(context,"Daha iyi deneyim için açık tema kullanın",Toast.LENGTH_LONG).show()
    }

    private fun setObservers() {
        viewModel.productData.observe(viewLifecycleOwner) { list ->
            adapter = ProductListAdapter(list) { position: Int ->
                findNavController().navigate(
                    ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(
                        list[position]
                    )
                )
            }
            binding.rvProductList.adapter = adapter
        }
        viewModel.productLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.productError.observe(viewLifecycleOwner) { isError ->
            binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        }
    }
}