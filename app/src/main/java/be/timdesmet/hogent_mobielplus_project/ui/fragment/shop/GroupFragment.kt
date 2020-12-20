package be.timdesmet.hogent_mobielplus_project.ui.fragment.shop

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentAuthLoginBinding
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentShopGroupBinding
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.ui.adapters.shop.GroupProductListAdapter
import be.timdesmet.hogent_mobielplus_project.ui.adapters.shop.GroupProductListListener
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop.GroupViewModel
import com.google.gson.Gson
import java.lang.Exception

class GroupFragment : Fragment() {

    private lateinit var binding : FragmentShopGroupBinding
    private lateinit var groupViewModel: GroupViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_group, container, false)
        val application = requireNotNull(this.activity).application
        val group = Gson().fromJson(arguments?.get("group").toString(), ProductGroup::class.java)

        val vmf = GroupViewModelFactory(application, group)
        groupViewModel = ViewModelProvider(this, vmf).get(GroupViewModel::class.java)
        binding.viewModel = groupViewModel
        binding.lifecycleOwner = this

        initListeners(binding)
        setupGroup()
        setupProducts()

        return binding.root
    }

    private fun setupGroup() {
        groupViewModel.group.observe(viewLifecycleOwner, Observer {
            try {
                binding.groupName.text = it.name
                binding.groupDescription.text = it.description
            }
            catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupProducts() {
        val manager = GridLayoutManager(activity, 1)
        binding.productList.layoutManager = manager
        val adapter = GroupProductListAdapter(GroupProductListListener(
            _viewProduct = {
                val productFragment = ProductFragment()
                val bundle = Bundle()
                bundle.putString("product", Gson().toJson(it))
                productFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_wrapper, productFragment)
                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ?.commit()
            }
        ))
        binding.productList.adapter = adapter
        groupViewModel.products.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.sortedBy { p -> p.pid })
        })
    }

    private fun initListeners(binding: FragmentShopGroupBinding) {
        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_wrapper, HomeFragment())
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                ?.commit()
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal class GroupViewModelFactory(val app: Application, val group: ProductGroup) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GroupViewModel::class.java)) return GroupViewModel(app, group) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}