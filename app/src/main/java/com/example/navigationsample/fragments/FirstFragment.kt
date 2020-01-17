package com.example.navigationsample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.databinding.FragmentFirstBinding
import com.example.navigationsample.viewModel.MainViewModel
import com.validatorcrawler.aliazaz.Validator


class FirstFragment : Fragment() {

    private lateinit var bi: FragmentFirstBinding
    private lateinit var vModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vModel = activity?.run { ViewModelProviders.of(this)[MainViewModel::class.java] }
            ?: throw Exception("Invalidate Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = FragmentFirstBinding.inflate(inflater, container, false)
        bi.callback = this

        bi.user01.text = vModel.getUser(0)?.username ?: ""
        /*bi.lstChatUser01.adapter = ArrayAdapter(activity,android.R.layout.simple_list_item_1,
            viewModel.getUser(0)?.msgLst
        )*/

        return bi.root
    }

    fun btnSend() {
        if (!formValidate()) return
        findNavController().navigate(FirstFragmentDirections.actionMainFragmentToSecondFragment())
    }

    fun formValidate(): Boolean {
        return Validator.emptyCheckingContainer(layoutInflater.context, bi.fldGrpB)
    }

}
