package com.example.listadedesejos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadedesejos.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), OnGameClickListener {

    private val games: ArrayList<Game> = generateList(5)
    private val adapter = GameAdapter(games, this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
    }
    private fun generateList(size: Int): ArrayList<Game>{
        val list = ArrayList<Game>()
        for (i in 0 until size){
            val game = Game("Game $i", 10.0f,null, "Developer $i")
            list.add(game)
        }

        return list
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
                if(result.resultCode == Activity.RESULT_OK){
                    val intent = result.data
                    val gameJSON = intent?.getStringExtra("GAME")
                    var game: Game = Gson().fromJson(gameJSON, Game::class.java)

                    if(intent?.extras?.containsKey("POSITION") == true){
                        var position = intent.getStringExtra("POSITION")?.toInt()
                        games[position!!] = game
                        adapter.notifyItemChanged(position)
                    }else{
                        games.add(0, game)
                        adapter.notifyItemInserted(0)
                    }
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

    override fun onGameClick(position: Int) {
        val intent = Intent (
            this,
            AddGameActivity::class.java,
        )

        var game: Game = games[position]
        var gameJson = Gson().toJson(game)

        intent.putExtra("POSITION", position.toString())
        intent.putExtra("GAME", gameJson)

        startForResult.launch(intent)
    }
}