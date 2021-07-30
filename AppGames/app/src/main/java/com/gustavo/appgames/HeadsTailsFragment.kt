package com.gustavo.appgames

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gustavo.appgames.databinding.FragmentHeadsTailsBinding

class HeadsTailsFragment: Fragment(R.layout.fragment_heads_tails) {

    private var _binding: FragmentHeadsTailsBinding? = null
    private val binding get() = _binding!!
    private var selectedFace: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadsTailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectHeadButton.setOnClickListener {
            selectedFace = 0
            setCoinFace()
        }

        binding.selectTailButton.setOnClickListener {
            selectedFace = 1
            setCoinFace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCoinFace(){
        if (binding.coinFaceImageView.visibility == VISIBLE){
            binding.coinFaceImageView.visibility = INVISIBLE
        }

        Toast.makeText(context, "Flipping the coin...   ", Toast.LENGTH_SHORT).show()

        Thread.sleep(2000)

        val randValue = (0..1).random()

        when(randValue){
            0 -> binding.coinFaceImageView.setImageResource(R.drawable.dogecoin_front)
            1 -> binding.coinFaceImageView.setImageResource(R.drawable.dogecoin_back)
        }

        if (randValue == selectedFace){
            binding.resultTextView.text = "YOU WIN!!!"
            Data.loggedUser!!   .winsAndDefeats[0].wins += 1
        } else {
            binding.resultTextView.text = "I'm Sorry! You lost, try again."
            Data.loggedUser!!.winsAndDefeats[0].defeats += 1
        }

        binding.coinFaceImageView.visibility = VISIBLE
    }
}