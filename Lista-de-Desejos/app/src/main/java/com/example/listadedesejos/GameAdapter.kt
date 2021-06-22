package com.example.listadedesejos

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(
    private val gameList: List<Game>,
    val listener: OnGameClickListener
    ): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(gameView: View): RecyclerView.ViewHolder(gameView), View.OnClickListener  {
        val name: TextView = gameView.findViewById(R.id.name)
        val developer: TextView = gameView.findViewById(R.id.developer)
        val price: TextView = gameView.findViewById(R.id.price)
        private val removeButton: ImageButton = gameView.findViewById(R.id.remove) as ImageButton
        
        init {
            removeButton.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            listener.onRemoveButtonClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val gameView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return GameViewHolder(gameView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentGame = gameList[position]
        holder.name.text = (currentGame.name)
        holder.developer.text = (currentGame.developer)
        holder.price.text = (currentGame.price.toString())

        if (position == 0){
            holder.name.setBackgroundColor(Color.BLUE)
        }
    }

    override fun getItemCount() = gameList.size
}