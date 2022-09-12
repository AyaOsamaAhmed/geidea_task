package com.aya.geidea_task.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aya.geidea_task.databinding.UserDetailsFragmentBinding
import com.aya.geidea_task.ui.viewModel.HomeViewModel

class UserDetailsFragment : Fragment() {

    private lateinit var binding: UserDetailsFragmentBinding
    private lateinit var viewModel : HomeViewModel
    val mainActivity  by lazy { activity  }
    


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = UserDetailsFragmentBinding.inflate(inflater , container , false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        var arg = arguments?.let { UserDetailsFragmentArgs.fromBundle(it) }

        val id = arg?.userId


        return binding.root
    }
}
