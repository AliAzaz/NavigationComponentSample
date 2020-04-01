package com.example.navigationsample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationsample.contract.User

class MainViewModel : ViewModel() {

    private var user = MutableLiveData<MutableList<User>>()
    var usersChat = MutableLiveData<MutableList<Pair<Int, String>>>()

    init {
        usersChat.value = mutableListOf()
    }

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
        user.value = userLst
    }

    fun updateChat(singleUser: User, msgLst: String) {
        val lst = getUser(singleUser.userID)
        lst?.let {
            lst.msgLst.add(msgLst)
            val mainLst = user.value
            mainLst?.map { if (it.userID == lst.userID) lst else it }
            user.value = mainLst
        }

        val messages = usersChat.value
        messages?.add(Pair(singleUser.userID, msgLst))
        usersChat.value = messages
    }

    fun getUserChat(index: Int): MutableList<String>? {
        return getUser(index)?.msgLst
    }
}
