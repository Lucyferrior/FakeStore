package com.sefakose.fakestore.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
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
        showToast()
        setObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.list_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.grid_switch -> {
                        viewModel.isGrid.value = (!viewModel.isGrid.value!!)
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showToast() {
        Toast.makeText(context, "Daha iyi deneyim için açık tema kullanın", Toast.LENGTH_LONG)
            .show()
    }

    private fun setObservers() {
        viewModel.isGrid.observe(viewLifecycleOwner) {
            binding.rvProductList.layoutManager = if (it) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
        }

        viewModel.productData.observe(viewLifecycleOwner) { list ->
            adapter = ProductListAdapter(list) { position ->
                findNavController().navigate(
                    ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(
                        list[position]
                    )
                )
            }
            binding.rvProductList.adapter = adapter
            adapter.updateList(list)
        }

        viewModel.productLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.productError.observe(viewLifecycleOwner) { isError ->
            binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        }
    }
}