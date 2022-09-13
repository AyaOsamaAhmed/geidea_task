package com.aya.geidea_task.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
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
import com.aya.geidea_task.ui.viewModel.HomeViewModel

class UserDetailsFragment : Fragment() {

    private lateinit var binding: UserDetailsFragmentBinding
    private lateinit var viewModel : HomeViewModel

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
            navController.navigate(R.id.action_UserDetailsFragment_to_HomeFragment)
        }, 5000)

       */
        //https://stackoverflow.com/questions/62971825/how-to-start-a-service-and-then-bind-to-it-from-a-fragment
        //https://stackoverflow.com/questions/16382186/implementing-a-count-down-timer-using-service-in-the-background

        startTimer(5000)
        return binding.root
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
