package com.example.navigationsample.fragments


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.R


class EndChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler().postDelayed({
            findNavController().navigate(EndChatFragmentDirections.actionEndChatFragmentToRegistrationFragment())
        }, 2000)

        return inflater.inflate(R.layout.fragment_end_chat, container, false)
    }
}
