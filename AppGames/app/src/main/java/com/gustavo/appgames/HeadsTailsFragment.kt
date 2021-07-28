package com.gustavo.appgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gustavo.appgames.databinding.FragmentHeadsTailsBinding

class HeadsTailsFragment: Fragment(R.layout.fragment_heads_tails) {

    private var _binding: FragmentHeadsTailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeadsTailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addWinButton.setOnClickListener {
            Data.loggedUser?.winsAndDefeats!![0].wins += 1
        }

        binding.addDefeatButton.setOnClickListener {
            Data.loggedUser?.winsAndDefeats!![0].defeats += 1
        }
    }
}