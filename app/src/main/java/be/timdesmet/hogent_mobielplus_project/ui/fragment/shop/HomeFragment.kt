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
import androidx.recyclerview.widget.LinearLayoutManager
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentShopHomeBinding
import be.timdesmet.hogent_mobielplus_project.services.UserService
import be.timdesmet.hogent_mobielplus_project.ui.adapters.shop.HomeProductGroupListAdapter
import be.timdesmet.hogent_mobielplus_project.ui.adapters.shop.HomeProductGroupListListener
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop.HomeViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentShopHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_home, container, false)
        val application = requireNotNull(this.activity).application

        val vmf = HomeViewModelFactory(application)
        homeViewModel = ViewModelProvider(this, vmf).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupProductGroups()
        setupBanner()
        setupClient()
    }

    private fun setupClient() {
        try {
            UserService.client.observe(viewLifecycleOwner, Observer {
                binding.textWelcomeUser.text = "Hi ${it?.firstname}!"
            })
        }
        catch (e: Exception) {
            Toast.makeText(activity, "Client details could not be loaded", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupProductGroups() {
        val manager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryList.layoutManager = manager
        val groupAdapter = HomeProductGroupListAdapter(HomeProductGroupListListener(
            _viewGroup = {
                val groupFragment = GroupFragment()
                val bundle = Bundle()
                bundle.putString("group", Gson().toJson(it))
                groupFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_wrapper, groupFragment)
                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ?.commit()
            }
        ))
        binding.categoryList.adapter = groupAdapter
        homeViewModel.productGroups.observe(viewLifecycleOwner, Observer {
            groupAdapter.submitList(it.sortedBy { g -> g.weight })
        })
    }

    private fun setupBanner() {
        homeViewModel.banner.observe(viewLifecycleOwner, Observer {
            try {
                binding.bannerTitle.text = Html.fromHtml(it.title)
                binding.bannerSubtitle.text = Html.fromHtml(it.subtitle)
                if(!it.image.isNullOrEmpty())
                    Picasso.get().load(it.image).into(binding.bannerImage)
            }
            catch (e: Exception) { }
        })
    }

    @Suppress("UNCHECKED_CAST")
    internal class HomeViewModelFactory(val app: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) return HomeViewModel(app) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}