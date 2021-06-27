package com.example.listadedesejos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.gson.Gson

class AddGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
    }

    fun saveGame(view: View) {
        val gameName = findViewById<EditText>(R.id.gameNameEditTextView) as EditText
        val gameDeveloper = findViewById<EditText>(R.id.gameDeveloperEditTextView) as EditText
        val gamePrice = findViewById<EditText>(R.id.gamePriceEditTextView) as EditText
        val gameLaunchDate = findViewById<EditText>(R.id.gameLaunchDateEditTextView) as EditText

        val newGame = Game(
            R.drawable.ic_baseline_adb_24,
            gameName.text.toString(),
            gamePrice.text.toString().toFloat(),
            null,
            gameDeveloper.text.toString(),
        )

        val newGameJSON = Gson().toJson(newGame)

        val intent = Intent()
        intent.putExtra("GAME", newGameJSON)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
