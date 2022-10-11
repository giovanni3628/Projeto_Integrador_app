package com.example.prototipo_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.prototipo_app.adapter.PostagemAdapter
import com.example.prototipo_app.databinding.FragmentDetailBinding
import com.example.prototipo_app.model.Postagem

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private var postagemSelecionada: Postagem? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        carregarDados()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun carregarDados() {
        postagemSelecionada = mainViewModel.postagemSelecionada
        if (postagemSelecionada != null) {
            binding.txtTitulo.setText(postagemSelecionada?.titulo)
            binding.txtDescricao.setText(postagemSelecionada?.descricao)
            binding.txtMeta.setText("Meta R$: " + postagemSelecionada?.meta?.toInt())
            binding.txtTema.setText(postagemSelecionada?.tema?.tema)

            Glide.with(this).load(postagemSelecionada!!.imagem).into(binding.imgPostagem)

            binding.progressBar.progress = 65
        }
    }
}