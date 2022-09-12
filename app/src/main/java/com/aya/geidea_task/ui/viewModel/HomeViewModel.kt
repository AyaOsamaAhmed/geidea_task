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
    var requestUserInDatabaseLiveData = MutableLiveData<Any>()
    var userData : ArrayList<User> = arrayListOf()


    init {

        getAllUsers()

    }

    fun getAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            requestUserLiveData.postValue(MainRepo.getAllUsers())
        }
    }

    fun setUsersInDatabase(user : User){
        viewModelScope.launch(Dispatchers.IO) {
            requestUserInDatabaseLiveData.postValue(App.dbApp.userDataBase.insertUser(user))
        }
    }

}