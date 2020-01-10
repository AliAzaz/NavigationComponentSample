package com.example.navigationsample.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.databinding.FragmentRegistrationBinding
import com.example.navigationsample.viewModel.MainViewModel
import com.validatorcrawler.aliazaz.Validator

class RegistrationFragment : Fragment() {

    private lateinit var bi: FragmentRegistrationBinding
    private lateinit var vModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = FragmentRegistrationBinding.inflate(inflater, container, false)
        bi.callback = this

        return bi.root
    }

    fun BtnStart() {
        if (!Validator.emptyCheckingContainer(layoutInflater.context, bi.fldGrpA))
            return

        // Set Users
        vModel.setUsers(listOf(bi.fName.text.toString(), bi.sName.text.toString()))

        // Navigation
        findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment())

    }
}
