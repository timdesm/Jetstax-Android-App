package be.timdesmet.hogent_mobielplus_project.ui.fragment.shop

import android.app.Application
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentShopGroupBinding
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentShopProductBinding
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop.ProductViewModel
import com.google.gson.Gson
import java.lang.Exception

class ProductFragment: Fragment() {

    private lateinit var binding : FragmentShopProductBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_product, container, false)
        val application = requireNotNull(this.activity).application

        val product = Gson().fromJson(arguments?.get("product").toString(), Product::class.java)
        val vmf = ProductViewModelFactory(application, product)
        productViewModel = ViewModelProvider(this, vmf).get(ProductViewModel::class.java)
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this

        initProduct(binding)
        initGroup(binding)
        initListeners(binding)


        return binding.root
    }

    private fun initProduct(binding: FragmentShopProductBinding) {
        productViewModel.product.observe(viewLifecycleOwner, Observer {
            try {
                binding.product = it
                binding.productDescription.text = Html.fromHtml(it.description)
            }
            catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initGroup(binding: FragmentShopProductBinding) {
        productViewModel.group.observe(viewLifecycleOwner, Observer {
            try {
                binding.productName.text = it.name + " - " + (productViewModel.product.value?.name ?: "")
            }
            catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initListeners(binding: FragmentShopProductBinding) {
        binding.backButton.setOnClickListener {
            val groupFragment = GroupFragment()
            val bundle = Bundle()
            bundle.putString("group", Gson().toJson(productViewModel.group.value))
            groupFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_wrapper, groupFragment)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                ?.commit()
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal class ProductViewModelFactory(val app: Application, val product: Product) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) return ProductViewModel(app, product) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}