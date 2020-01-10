package com.example.navigationsample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationsample.contract.User

class MainViewModel : ViewModel() {

    var user = MutableLiveData<MutableList<User>>()

    private fun getUserList(): MutableList<User>? {
        return user.value
    }

    fun setUsers(users: List<String>) {
        for (i in users.indices)
            user.value?.toMutableList()?.map { i to User(i, users[i], mutableListOf()) }
    }

    fun updateChat(singleUser: User) {
        val lst = getUserList()?.get(singleUser.userID)
        lst?.msgLst = singleUser.msgLst
    }


}
