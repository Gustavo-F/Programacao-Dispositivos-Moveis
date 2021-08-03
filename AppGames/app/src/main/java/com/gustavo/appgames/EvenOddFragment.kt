package com.gustavo.appgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gustavo.appgames.databinding.FragmentEvenOddBinding

class EvenOddFragment: Fragment(R.layout.fragment_even_odd) {
    private var _binding: FragmentEvenOddBinding? = null
    private val binding get() = _binding!!
    private var choice: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvenOddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.evenButton.setOnClickListener {
            choice = 0
            setResult()
        }

        binding.oddButton.setOnClickListener {
            choice = 1
            setResult()
        }
    }

    private fun setResult(){
        if (binding.numberEditText.text.isBlank()) {
            Toast.makeText(context, "Enter your number", Toast.LENGTH_SHORT).show()
        } else if (binding.numberEditText.text.toString().toInt() > 10){
            Toast.makeText(context, "Enter number between 0 and 10", Toast.LENGTH_SHORT).show()
        } else {
            binding.userChoice.setText(binding.numberEditText.text.toString())
            val randValue = (0..10).random()
            var total = binding.numberEditText.text.toString().toInt() + randValue
            binding.machineChoice.setText(randValue.toString())

            var result = total % 2

            if(result == choice){
                binding.resultTextView.setText("YOU WIN!!!")
                Data.loggedUser?.winsAndDefeats!![2].wins += 1
            }else{
                binding.resultTextView.setText("I'm Sorry! You lost, try again.")
                Data.loggedUser?.winsAndDefeats!![2].defeats += 1
            }
        }

        choice = -1
    }
}