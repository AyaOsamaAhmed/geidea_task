package com.aya.geidea_task.ui.fragment

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.aya.geidea_task.R
import com.aya.geidea_task.databinding.UserDetailsFragmentBinding
import com.aya.geidea_task.domain.response.DetailsResponse
import com.aya.geidea_task.service.BoundService
import com.aya.geidea_task.service.BoundService.MyBinder
import com.aya.geidea_task.ui.viewModel.HomeViewModel


class UserDetailsFragment : Fragment() {

    private lateinit var binding: UserDetailsFragmentBinding
    private lateinit var viewModel : HomeViewModel

    var mBoundService: BoundService? = null
    var mServiceBound = false


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

        binding = UserDetailsFragmentBinding.inflate(inflater , container , false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        var arg = arguments?.let { UserDetailsFragmentArgs.fromBundle(it) }

        val id = arg?.userId
        if(id != null)
            viewModel.getDetailsUser(id)


        viewModel.requestDetailsUserLiveData.observe(viewLifecycleOwner, Observer { it ->
            val data = it as DetailsResponse

            binding.model = data.data
        })

        //countDown
      /*  Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent that will start.
           // navController.navigate(R.id.action_UserDetailsFragment_to_HomeFragment)
            binding.countDown.text = mBoundService?.getTimestamp()

        }, 1000)

       */

        //https://stackoverflow.com/questions/62971825/how-to-start-a-service-and-then-bind-to-it-from-a-fragment
        //https://stackoverflow.com/questions/16382186/implementing-a-count-down-timer-using-service-in-the-background
        //------https://www.truiton.com/2014/11/bound-service-example-android/
        //------https://proandroiddev.com/bound-and-foreground-services-in-android-a-step-by-step-guide-5f8362f4ae20

      //  startTimer(5000)

        return binding.root
    }

     override fun onStart() {
        super.onStart()
        val intent = Intent(activity, BoundService::class.java)
        requireActivity().startService(intent)
        requireActivity().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

      override fun onStop() {
        super.onStop()
        if (mServiceBound) {
            requireActivity().unbindService(mServiceConnection)
            mServiceBound = false
        }
    }

    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as MyBinder
            mBoundService = myBinder.service
            mServiceBound = true
            startTimer(5000)
            binding.countDown.text = mBoundService?.getTimestamp()
        }
    }

    //start timer function
    fun startTimer(time : Long) {
        var cTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDown.text ="seconds remaining: " + millisUntilFinished / 1000
            }
            override fun onFinish() {
                navController.navigate(R.id.action_UserDetailsFragment_to_HomeFragment)
            }
        }
        cTimer.start()
    }


    //cancel timer
/*    fun cancelTimer() {
        if (cTimer != null) cTimer.cancel()
    }
*/

}
