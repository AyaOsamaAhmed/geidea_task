package com.aya.geidea_task.ui.fragment


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.aya.geidea_task.R
import com.aya.geidea_task.databinding.HomeFragmentBinding
import com.aya.geidea_task.domain.model.User
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


        if(isOnline(requireContext())){
            viewModel.getAllUsers()
        }else{
           viewModel.getUsersInDatabase()
        }

        viewModel.requestUserLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as MainResponse
            saveUsersInDatabase(data.data)
            adapter = UserAdapter(data.data,this)
            binding.recyclerUsers.adapter = adapter
        })

        viewModel.requestUserInDatabaseLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as ArrayList<User>

            if(data.size == 0){
        var  alerDialogbuilder = AlertDialog.Builder(context)
                alerDialogbuilder.setTitle("Internet / Wifi Connection For first time ! ")
                alerDialogbuilder.setMessage("Turn on Wifi/Internet ")

                alerDialogbuilder.setCancelable(false)
                alerDialogbuilder.setPositiveButton("yes",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        startActivityForResult(
                            Intent(Settings.ACTION_WIFI_SETTINGS),
                            0
                        )
                    })
            val   alertDialog = alerDialogbuilder.create()
                alertDialog.show()
            }else{
                adapter = UserAdapter(data,this)
                binding.recyclerUsers.adapter = adapter
            }


        })
        return binding.root
    }

    private fun saveUsersInDatabase(data: ArrayList<User>) {
        repeat(data.size){item ->
            viewModel.setUsersInDatabase(data[item])
        }

    }

    override fun onClickDetails(id: Int) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToUserDetailsFragment(id))
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


}
