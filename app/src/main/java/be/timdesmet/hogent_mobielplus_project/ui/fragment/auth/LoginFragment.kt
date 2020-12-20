package be.timdesmet.hogent_mobielplus_project.ui.fragment.auth

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentAuthLoginBinding
import be.timdesmet.hogent_mobielplus_project.services.UserService
import be.timdesmet.hogent_mobielplus_project.ui.MainActivity
import be.timdesmet.hogent_mobielplus_project.ui.SplashscreenActivity
import be.timdesmet.hogent_mobielplus_project.utils.Constants
import be.timdesmet.hogent_mobielplus_project.ui.viewmodels.auth.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var application: Application
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAuthLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_login, container, false)
        application = requireNotNull(this.activity).application

        val vmf = LoginViewModelFactory(application)
        loginViewModel = ViewModelProvider(this, vmf).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this

        // Setup listeners
        initObServers()
        initListeners(binding)

        return binding.root
    }

    private fun initObServers() {
        loginViewModel.token.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                Handler().postDelayed({
                    val intent = Intent(activity, SplashscreenActivity::class.java)
                    this.startActivity(intent)
                }, 1000)
            }
        })
    }

    private fun initListeners(binding: FragmentAuthLoginBinding) {
        // Add click listeners to password forgot link
        binding.forgotLink.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.drawer_layout, PasswordFragment())
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.commit()
        }
        // Add click listeners signup link
        binding.signupLink.setOnClickListener {
            val webView = WebView(application)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(Constants.SIGNUP_URL)
            AlertDialog.Builder(this.activity)
                .setView(webView)
                .setNeutralButton("Close", null)
                .show()
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal class LoginViewModelFactory(val app: Application) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) return LoginViewModel(app) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}