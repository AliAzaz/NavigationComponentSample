package com.example.navigationsample.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.contract.User
import com.example.navigationsample.databinding.FragmentSecondBinding
import com.example.navigationsample.viewModel.MainViewModel
import com.validatorcrawler.aliazaz.Validator

class SecondFragment : Fragment() {

    private lateinit var bi: FragmentSecondBinding
    private lateinit var vModel: MainViewModel
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vModel = activity?.run { ViewModelProviders.of(this)[MainViewModel::class.java] }
            ?: throw Exception("f")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = FragmentSecondBinding.inflate(inflater, container, false)
        bi.callback = this

        user = vModel.getUser(1)
        bi.user.text = user?.username!!.toUpperCase()
        bi.lstChatUser.adapter =
            activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    vModel.usersChat.value?.map { dt -> if (dt.first == 1) "You:${dt.second}" else "He:${dt.second}" }
                        ?: listOf()
                )
            }

        return bi.root
    }


    fun btnSend() {
        if (!formValidate()) return

        user?.let {
            vModel.updateChat(user!!, bi.txtMessage.text.toString())
        }

        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
    }

    private fun formValidate(): Boolean {
        return Validator.emptyCheckingContainer(layoutInflater.context, bi.fldGrpB)
    }

    fun btnEnd() {
        bi.btnEnd.hideKeyboard()
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToEndChatFragment())
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
