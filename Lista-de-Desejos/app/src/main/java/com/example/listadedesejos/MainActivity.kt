package com.example.listadedesejos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), OnGameClickListener {

    private val games: ArrayList<Game> = generateList(500)
    private val adapter= GameAdapter(games, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
    private fun generateList(size: Int): ArrayList<Game>{
        val list = ArrayList<Game>()
        for (i in 0 until size){
            val game = Game(R.drawable.ic_baseline_adb_24, "Game $i", 10.0f,null, "Developer $i")
            list.add(game)
        }

        return list
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
                if(result.resultCode == Activity.RESULT_OK){
                    val intent = result.data
                    val newGameJSON = intent?.getStringExtra("GAME")

                    val newGame: Game = Gson().fromJson(newGameJSON, Game::class.java)
                    games.add(0, newGame)
                    adapter.notifyItemInserted(0)
                }
    }

    fun addGame(view: View) {
        val intent = Intent(
            this,
            AddGameActivity::class.java
        )
        startForResult.launch(intent)
    }

    override fun onRemoveButtonClick(position: Int) {
        games.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}