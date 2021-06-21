package com.example.listadedesejos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val jogos: ArrayList<Jogo> = generateList(500)
    private val adapter= JogoAdapter(jogos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
    fun generateList(size: Int): ArrayList<Jogo>{
        val list = ArrayList<Jogo>()
        for (i in 0 until size){
            val jogo = Jogo(R.drawable.ic_baseline_adb_24, "Jogo $i", "Desenvolvedora $i")
            list.add(jogo)
        }

        return list
    }

    fun insertButton(view: View) {
        val jogo = Jogo(R.drawable.ic_baseline_adb_24, "Novo Jogo", "desenvolvedora")
        val position = Random.nextInt(0, 8)
        jogos.add(position, jogo)
        adapter.notifyItemInserted(position)
    }

    fun removeButton(view: View) {
        val position = (0..8).random()
        jogos.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}