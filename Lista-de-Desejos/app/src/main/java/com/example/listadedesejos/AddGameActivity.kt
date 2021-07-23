package com.example.listadedesejos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listadedesejos.databinding.ActivityAddGameBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat

class AddGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddGameBinding

    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityAddGameBinding.inflate(layoutInflater)
        position = -1

        if (intent.extras?.containsKey("POSITION") == true){
            position = intent?.getStringExtra("POSITION")!!.toInt()
            var game: Game = Gson().fromJson(intent?.getStringExtra("GAME"), Game::class.java)

            binding.gameNameEditTextView.setText(game.name)
            binding.gamePriceEditTextView.setText(game.price.toString())
            binding.gameDeveloperEditTextView.setText(game.developer)
            binding.gameLaunchDateEditTextView.setText(game.launchDate.toString())
        }

        val view = binding.root
        setContentView(view)
    }

    fun saveGame(view: View) {
        if (binding.gameNameEditTextView.text.toString().isBlank()){
            Toast.makeText(this, "Name is a required field...", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.gameDeveloperEditTextView.text.toString().isBlank()){
            Toast.makeText(this, "Developer is a required field...", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.gameLaunchDateEditTextView.text.toString().isBlank()){
            Toast.makeText(this, "Launch date is a required field...", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.gamePriceEditTextView.text.toString().isBlank()){
            Toast.makeText(this, "Price is a required field...", Toast.LENGTH_SHORT).show()
            return
        }

        var dateFormat = SimpleDateFormat("dd/MM/yyyy")
        var launchDate = dateFormat.parse(binding.gameLaunchDateEditTextView.text.toString())

        val newGame = Game(
            binding.gameNameEditTextView.text.toString(),
            binding.gamePriceEditTextView.text.toString().toFloat(),
            launchDate,
            binding.gameDeveloperEditTextView.text.toString(),
        )

        val newGameJSON = Gson().toJson(newGame)
        val intent = Intent()
        intent.putExtra("GAME", newGameJSON)

        if (position > -1){
            intent.putExtra("POSITION", position.toString())
        }

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
