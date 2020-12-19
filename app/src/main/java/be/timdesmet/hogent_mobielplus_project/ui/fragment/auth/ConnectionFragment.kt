package be.timdesmet.hogent_mobielplus_project.ui.fragment.auth

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.databinding.FragmentAuthConnectionBinding

class ConnectionFragment : Fragment() {

    private lateinit var application: Application

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentAuthConnectionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth_connection, container, false)
        application = requireNotNull(this.activity).application

        binding.lifecycleOwner = this

        return binding.root
    }
}