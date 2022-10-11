package com.example.prototipo_app.adapter

import android.annotation.SuppressLint


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prototipo_app.databinding.CardFeedBinding
import com.example.prototipo_app.model.Postagem

class PostagemAdapter(
    val taskClickListener: TaskClickListener,
) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>() {


    private var listPostagem = emptyList<Postagem>()

    class PostagemViewHolder(val binding: CardFeedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        return PostagemViewHolder(CardFeedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val context = holder.itemView.context
        val postagem = listPostagem[position]
        holder.binding.tvTitulo.text = postagem.titulo
        holder.binding.tvMeta.text = "Meta: R$" + postagem.meta.toString()
        holder.binding.tvCategoria.text = postagem.tema.tema


        Glide.with(context).load(postagem.imagem).into(holder.binding.imgLink)


        // Teste Samuel & Henrique - Tela Detalhes ----

        holder.binding.btInfo.setOnClickListener {
            taskClickListener.onDetailClick(postagem)
        }

        holder.binding.cardShare.setOnClickListener {
            taskClickListener.onClickShare()
        }

        holder.itemView.setOnClickListener {
            taskClickListener.onTaskClickListener(postagem)
        }


            holder.itemView.setOnClickListener {
                taskClickListener.onTaskClickListener(postagem)
            }
    }

    override fun getItemCount(): Int {
        return listPostagem.size
    }

    fun setList(list: List<Postagem>) {
        listPostagem = list
        notifyDataSetChanged()
    }


    private fun showAlertDialog(id: Long){
        AlertDialog.Builder(context)
            .setTitle("Excluir Postagem")
            .setMessage("Deseja excluir a postagem?")
            .setPositiveButton("Sim"){
                    _,_ -> mainViewModel.deletePostagem(id)
            }
            .setNegativeButton("Não"){
                    _,_ ->
            }.show()
    }
}