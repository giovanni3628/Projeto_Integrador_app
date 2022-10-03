package com.example.prototipo_app

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.prototipo_app.databinding.FragmentPostagemBinding
import com.example.prototipo_app.model.Postagem


class PostagemFragment : Fragment() {

    private lateinit var binding: FragmentPostagemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostagemBinding.inflate(layoutInflater, container, false)

        binding.buttonPostar.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }
    private fun validarCampos(
        titulo: String,
        descricao: String,
        meta: String,
    ): Boolean{
        return !(
                (titulo == "" || titulo.length < 3 || titulo.length > 20) ||
                        (descricao == "" || descricao.length < 10 || descricao.length > 250) ||
                        (meta == "" || meta.isEmpty())
                )
    }
    private fun inserirNoBanco(){
        val titulo = binding.TextTitulo.text.toString()
        val link = binding.textLink.text.toString()
        val descricao = binding.textDescricao.text.toString()
        val meta = binding.textMeta.text.toString()
        val categoria = binding.SpinnerCategoria.toString()

        if (validarCampos(titulo, descricao, meta)){
            val postagem = Postagem(titulo, link, meta, descricao, categoria)
            Toast.makeText(context, "Postagem criada!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_postagemFragment_to_feedFragment)

        }else{
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }

    }
}