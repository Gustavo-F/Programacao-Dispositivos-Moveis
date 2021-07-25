package com.gustavo.appgames

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = getView()?.findViewById<EditText>(R.id.usernameRegisterEditText)
        val passwordEditText = getView()?.findViewById<EditText>(R.id.passwordRegisterEditText)
        val confirmPasswordEditText = getView()?.findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = getView()?.findViewById<Button>(R.id.registerButton)
        val cancelButton = getView()?.findViewById<Button>(R.id.cancelButton)

        registerButton?.setOnClickListener{
            if (!checkUsername(usernameEditText?.text.toString())) {

            } else if (!checkPassword(passwordEditText?.text.toString(), confirmPasswordEditText?.text.toString())) {

            } else {
                Toast.makeText(this.context, "Return True", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton?.setOnClickListener{activity?.onBackPressed()}
    }

    private fun checkUsername(username: String) : Boolean{
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

    private fun checkPassword(password: String, confirmPassword: String) : Boolean{
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