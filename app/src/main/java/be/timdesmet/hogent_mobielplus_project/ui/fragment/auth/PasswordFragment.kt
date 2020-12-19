package be.timdesmet.hogent_mobielplus_project.ui.fragment.auth

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentAuthPasswordBinding
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.auth.PasswordViewModel

class PasswordFragment : Fragment() {

    private lateinit var passwordViewMode: PasswordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAuthPasswordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_password, container, false)
        val application = requireNotNull(this.activity).application

        val vmf = PasswordViewModelFactory(application)
        passwordViewMode = ViewModelProvider(this, vmf).get(PasswordViewModel::class.java)
        binding.viewModel = passwordViewMode
        binding.lifecycleOwner = this

        initListeners(binding)

        return binding.root
    }

    private fun initListeners(binding: FragmentAuthPasswordBinding) {
        // Add click listeners to login link
        binding.loginLink.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.drawer_layout, LoginFragment())
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                ?.commit()
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal class PasswordViewModelFactory(val app: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PasswordViewModel::class.java)) return PasswordViewModel(app) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}