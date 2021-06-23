package com.example.listadedesejos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.security.spec.PSSParameterSpec
import kotlin.random.Random

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
            val jogo = Game(R.drawable.ic_baseline_adb_24, "Game $i", 10.0f,null, "Developer $i")
            list.add(jogo)
        }

        return list
    }

    fun addGame(view: View) {
        val intent = Intent(this, AddGameActivity::class.java)
        startActivity(intent)
//        val jogo = Game(R.drawable.ic_baseline_adb_24, "New Game", 10.0f,null, "Developer")
//        val position = Random.nextInt(0, 8)
//        games.add(position, jogo)
//        adapter.notifyItemInserted(position)
    }

    override fun onRemoveButtonClick(position: Int) {
        games.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}