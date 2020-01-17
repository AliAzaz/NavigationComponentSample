package com.example.navigationsample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationsample.contract.User

class MainViewModel : ViewModel() {

    private var user = MutableLiveData<MutableList<User>>()

    private fun getUserList(): MutableList<User>? {
        return user.value
    }

    fun getUser(index: Int): User? {
        return user.value?.find { it.userID == index }
    }

    fun setUsers(users: List<String>) {
        val userLst = mutableListOf<User>()
        for (i in users.indices)
            userLst.add(User(i, users[i], mutableListOf()))
        user.postValue(userLst)
    }

    fun updateChat(singleUser: User) {
        val lst = getUserList()?.get(singleUser.userID)
        lst?.msgLst = singleUser.msgLst
    }


}
