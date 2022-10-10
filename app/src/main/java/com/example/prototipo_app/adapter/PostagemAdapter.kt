package com.example.prototipo_app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prototipo_app.MainViewModel
import com.example.prototipo_app.databinding.CardFeedBinding
import com.example.prototipo_app.model.Postagem


class PostagemAdapter(
    val detailClickListener: DetailClickListener,
    val mainViewModel: MainViewModel
) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>() {

    private var listPostagem = emptyList<Postagem>()

    class PostagemViewHolder(val binding: CardFeedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        return PostagemViewHolder(CardFeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = listPostagem[position]
        holder.binding.tvTitulo.text = postagem.titulo
        holder.binding.tvMeta.text = "Meta: R$" + postagem.meta.toString()
        holder.binding.tvCategoria.text = "Categoria:" + postagem.tema.tema

        // Teste Samuel & Henrique - Tela Detalhes ----

        holder.binding.btInfo.setOnClickListener {
            detailClickListener.onTaskClickListener()
        }

        holder.binding.cardShare.setOnClickListener {
            detailClickListener.onClickShare()
        }
    }

    override fun getItemCount(): Int {
        return listPostagem.size
    }

    fun setList(list: List<Postagem>) {
        listPostagem = list
        notifyDataSetChanged()
    }

}