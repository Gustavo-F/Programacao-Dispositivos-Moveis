package com.gustavo.appgames

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gustavo.appgames.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            var username = binding.usernameRegisterEditText.text.toString()
            var password = binding.passwordRegisterEditText.text.toString()

            if (checkUsername() && checkPassword()) {
                var user: User = User(username, password, ArrayList<WinAndDefeat>())

                for(game in Data.games){
                    user.winsAndDefeats.add(WinAndDefeat(game))
                }

                Data.users.add(user)
                Data.loggedUser = user

                Toast.makeText(this.context, "User registered success!", Toast.LENGTH_SHORT).show()
                var action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }

        binding.cancelButton.setOnClickListener{activity?.onBackPressed()}
    }

    private fun checkUsername() : Boolean{
        var username = binding.usernameRegisterEditText.text.toString()

        return when {
            username.isBlank() -> {
                Toast.makeText(this.context, "Enter the username.", Toast.LENGTH_SHORT).show()
                false
            }
            username.length < 6 -> {
                Toast.makeText(this.context, "Username should contain at least 6 characters.", Toast.LENGTH_SHORT).show()
                false
            }
            username.contains(" ") -> {
                Toast.makeText(this.context, "Username cannot contain spaces.", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }

    }

    private fun checkPassword() : Boolean{
        var password = binding.passwordRegisterEditText.text.toString()
        var confirmPassword = binding.confirmPasswordEditText.text.toString()

       return when {
            password.isBlank() -> {
                Toast.makeText(this.context, "Enter the password.", Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 6 -> {
                Toast.makeText(this.context, "Password should contain at least 6 characters.", Toast.LENGTH_SHORT).show()
                false
            }
            confirmPassword.isBlank() -> {
                Toast.makeText(this.context, "Confirm your password.", Toast.LENGTH_SHORT).show()
                false
            }
            password != confirmPassword -> {
                Toast.makeText(this.context, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }

    }
}