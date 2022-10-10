package com.example.prototipo_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.prototipo_app.MainViewModel
import com.example.prototipo_app.databinding.CardFeedBinding
import com.example.prototipo_app.model.Postagem

class PostagemAdapter (
    val taskClickListener: TaskClickListener,
    val mainViewModel: MainViewModel,
    val context: Context,
        ) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder> () {

    private var listPostagem = emptyList<Postagem>()

    class PostagemViewHolder (val binding: CardFeedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        return PostagemViewHolder(CardFeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = listPostagem[position]
            holder.binding.tvTitulo.text = postagem.titulo
            holder.binding.tvMeta.text = postagem.meta.toString()
            holder.binding.tvCategoria.text = postagem.tema.tema

            holder.itemView.setOnClickListener {
                taskClickListener.onTaskClickListener(postagem)
            }

    }

    override fun getItemCount(): Int {
        return listPostagem.size
    }

    fun setList(list: List<Postagem>){
        listPostagem = list
        notifyDataSetChanged()
    }
}