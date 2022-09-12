package com.aya.geidea_task.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.aya.geidea_task.R
import com.aya.geidea_task.databinding.HomeFragmentBinding
import com.aya.geidea_task.domain.response.MainResponse
import com.aya.geidea_task.ui.adapter.UserAdapter
import com.aya.geidea_task.ui.interfaces.onClick
import com.aya.geidea_task.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() , onClick {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel : HomeViewModel

    private lateinit var adapter : UserAdapter

    private val navController by lazy {
        val navHostFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.homeframelayout) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomeFragmentBinding.inflate(inflater , container , false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.requestUserLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as MainResponse

            adapter = UserAdapter(data.data,this)
            binding.recyclerUsers.adapter = adapter
        })

        return binding.root
    }

    override fun onClickDetails(id: Int) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToUserDetailsFragment(id))
    }
}
