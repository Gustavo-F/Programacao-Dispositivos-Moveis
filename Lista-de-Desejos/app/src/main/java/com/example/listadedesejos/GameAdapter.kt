package com.example.listadedesejos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadedesejos.databinding.ItemBinding

class GameAdapter(
    private val gameList: List<Game>,
    val listener: OnGameClickListener,
    ): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

        inner class GameViewHolder(private val gameBinding: ItemBinding): RecyclerView.ViewHolder(gameBinding.root), View.OnClickListener  {

        fun bind(game: Game){
            gameBinding.name.text = game.name
            gameBinding.price.text = game.price.toString()
            gameBinding.developer.text = game.developer
            gameBinding.launchDate.text = game.launchDate.toString()
            gameBinding.remove.setOnClickListener(this)
        }

        init {
            gameBinding.gameLinearLayout.setOnClickListener{
                listener.onGameClick(adapterPosition)
            }
        }

        override fun onClick(v: View?) {
            listener.onRemoveButtonClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val gameBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(gameBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentGame = gameList[position]
        holder.bind(currentGame)
    }

    override fun getItemCount() = gameList.size
}
