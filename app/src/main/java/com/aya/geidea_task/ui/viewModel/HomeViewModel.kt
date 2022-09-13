package com.aya.geidea_task.ui.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.geidea_task.domain.model.User
import com.aya.geidea_task.repo.MainRepo
import com.aya.geidea_task.utils.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {


    var requestUserLiveData = MutableLiveData<Any>()
    var requestDetailsUserLiveData = MutableLiveData<Any>()


    var requestUserInDatabaseLiveData = MutableLiveData<Any>()
    var userData : ArrayList<User> = arrayListOf()


    init {


    }

    fun getAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            requestUserLiveData.postValue(MainRepo.getAllUsers())
        }
    }

    fun getDetailsUser(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            requestDetailsUserLiveData.postValue(MainRepo.getDetailsUser(id))
        }
    }


    fun setUsersInDatabase(user : User){
        viewModelScope.launch(Dispatchers.IO) {
             App.databaseApp.userDataBase.insertUser(user)
        }
    }

    fun getUsersInDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            requestUserInDatabaseLiveData.postValue(App.databaseApp.userDataBase.getUsers())
        }
    }

}