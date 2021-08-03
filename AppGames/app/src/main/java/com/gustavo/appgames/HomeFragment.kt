package com.gustavo.appgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.gustavo.appgames.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            var action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.welcomeTextView.text = "Welcome ${Data.loggedUser?.username}"

        binding.winsTextView.text = "Wins: ${Data.loggedUser?.winsAndDefeats!![0].wins}"
        binding.defeatsTextView.text = "Defeats: ${Data.loggedUser?.winsAndDefeats!![0].defeats}"

        binding.rollDiceWins.text = "Wins: ${Data.loggedUser?.winsAndDefeats!![1].wins}"
        binding.rollDiceDefeats.text = "Defeats: ${Data.loggedUser?.winsAndDefeats!![1].defeats}"

        binding.evenOrOddWins.text = "Wins: ${Data.loggedUser?.winsAndDefeats!![2].wins}"
        binding.evenOrOddDefeats.text = "Defeats: ${Data.loggedUser?.winsAndDefeats!![2].defeats}"

        binding.headstailsLinearLayout.setOnClickListener {
            var action = HomeFragmentDirections.actionHomeFragmentToHeadsTailsFragment()
            findNavController().navigate(action)
        }

        binding.rollDiceLinearLayout.setOnClickListener{
            var action = HomeFragmentDirections.actionHomeFragmentToRollDiceFragment()
            findNavController().navigate(action)
        }

        binding.evenOrOddLinearLayout.setOnClickListener{
            var action = HomeFragmentDirections.actionHomeFragmentToEvenOddFragment()
            findNavController().navigate(action)
        }
    }
}