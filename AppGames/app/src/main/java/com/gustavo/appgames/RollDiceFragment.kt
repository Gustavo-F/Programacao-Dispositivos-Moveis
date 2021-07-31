package com.gustavo.appgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gustavo.appgames.databinding.FragmentRolldiceBinding

class RollDiceFragment: Fragment(R.layout.fragment_rolldice){

    private var _binding: FragmentRolldiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRolldiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rollDiceButton.setOnClickListener {
            if (binding.selectFaceEditText.text.toString().isNotBlank()) {
                if (binding.diceResultImageView.visibility == View.INVISIBLE) {
                    binding.diceResultImageView.visibility = View.VISIBLE
                }

                var selected: Int = binding.selectFaceEditText.text.toString().toInt()

                if (selected < 1 || selected > 6) {
                    Toast.makeText(
                        context,
                        "Invalid selection. Enter a number between 1 and 6.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val randValue = (1..6).random()

                    when(randValue){
                        1 -> binding.diceResultImageView.setImageResource(R.drawable.dice_1)
                        2 -> binding.diceResultImageView.setImageResource(R.drawable.dice_2)
                        3 -> binding.diceResultImageView.setImageResource(R.drawable.dice_3)
                        4 -> binding.diceResultImageView.setImageResource(R.drawable.dice_4)
                        5 -> binding.diceResultImageView.setImageResource(R.drawable.dice_5)
                        6 -> binding.diceResultImageView.setImageResource(R.drawable.dice_6)
                    }

                    if (selected == randValue) {
                        Data.loggedUser?.winsAndDefeats!![1].wins += 1
                        binding.resultDiceTextView.text = "YOU WIN!!!"
                    } else {
                        Data.loggedUser?.winsAndDefeats!![1].defeats += 1
                        binding.resultDiceTextView.text = "I'm Sorry! You lost, try again."
                    }
                }
            } else {
                Toast.makeText(context, "Please enter a number between 1 and 6.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}