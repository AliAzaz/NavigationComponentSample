package com.example.navigationsample.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.navigationsample.databinding.FragmentSecondBinding
import com.example.navigationsample.viewModel.MainViewModel

class SecondFragment : Fragment() {

    private lateinit var bi: FragmentSecondBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = FragmentSecondBinding.inflate(inflater, container, false)
        bi.callback = this

        bi.user02.text = viewModel.getUser(0)?.username ?: ""
        /*bi.lstChatUser01.adapter = ArrayAdapter(activity,android.R.layout.simple_list_item_1,
            viewModel.getUser(0)?.msgLst
        )*/


        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }
}
