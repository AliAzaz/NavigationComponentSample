package com.example.navigationsample.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.databinding.FragmentSecondBinding
import com.example.navigationsample.viewModel.MainViewModel
import com.validatorcrawler.aliazaz.Validator

class SecondFragment : Fragment() {

    private lateinit var bi: FragmentSecondBinding
    private lateinit var vModel: MainViewModel

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

        bi.user02.text = vModel.getUser(1)?.username ?: ""
        /*bi.lstChatUser01.adapter = ArrayAdapter(activity,android.R.layout.simple_list_item_1,
            viewModel.getUser(0)?.msgLst
        )*/


        return bi.root
    }


    fun btnSend() {
        if (!formValidate()) return
        findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToMainFragment())
    }

    fun formValidate(): Boolean {
        return Validator.emptyCheckingContainer(layoutInflater.context, bi.fldGrpC)
    }
}
