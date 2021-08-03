package com.gustavo.appgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gustavo.appgames.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Data.games.add(Game("Heads or Tails"))
        Data.games.add(Game("Roll Dice"))
        Data.games.add(Game("Even Or Odd"))

        binding.registerLoginButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.loginButton.setOnClickListener{
            if (checkLoginFields()){
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLoginFields(): Boolean{
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (username.isBlank()) {
            Toast.makeText(context, "Enter the username.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isBlank()) {
            Toast.makeText(context, "Enter your password.", Toast.LENGTH_SHORT).show()
            return false
        }

        for (user in Data.users){
            if (user.username == username && user.password == password){
                Data.loggedUser = user
                return true
            }
        }

        return false
    }
}