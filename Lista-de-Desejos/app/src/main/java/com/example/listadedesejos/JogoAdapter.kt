package com.example.listadedesejos

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JogoAdapter(private val jogoList: List<Jogo>): RecyclerView.Adapter<JogoAdapter.JogoViewHolder>() {

    class JogoViewHolder(jogoView: View): RecyclerView.ViewHolder(jogoView){
        val image: ImageView = jogoView.findViewById(R.id.imageView)
         val titulo: TextView = jogoView.findViewById(R.id.title)
         val info: TextView = jogoView.findViewById(R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoViewHolder {
        val jogoView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return JogoViewHolder(jogoView)
    }

    override fun onBindViewHolder(holder: JogoViewHolder, position: Int) {
        val jogoAtual = jogoList[position]
        holder.image.setImageResource(jogoAtual.imagem)
        holder.titulo.text = (jogoAtual.nome)
        holder.info.text = (jogoAtual.desenvolvedora)

        if (position == 0){
            holder.titulo.setBackgroundColor(Color.BLUE)
        }
    }

    override fun getItemCount() = jogoList.size
}