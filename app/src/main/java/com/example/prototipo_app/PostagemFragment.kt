package com.example.prototipo_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.prototipo_app.databinding.FragmentPostagemBinding
import com.example.prototipo_app.model.Categoria
import com.example.prototipo_app.model.Postagem


class PostagemFragment : Fragment() {

    private lateinit var binding: FragmentPostagemBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoriaSelecionada = 0L
    private var postagemSelecionada: Postagem? = null

    private var postagemSelecionada: Postagem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostagemBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listCategoria()
        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
            response -> Log.d("requisicao", response.body().toString())
            spinnerCategoria(response.body())
        }

        binding.buttonPostar.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    fun spinnerCategoria (listCategoria: List<Categoria>?){
        if (listCategoria != null){
            binding.SpinnerCategoria.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listCategoria
                )
            binding.SpinnerCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.SpinnerCategoria.selectedItem as Categoria

                        categoriaSelecionada = selected.id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
        }

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
        val categoria = Categoria(categoriaSelecionada, null, null, null)

        if (validarCampos(titulo, descricao, meta)){
            val salvar: String

            if (postagemSelecionada != null){
                salvar = "Postagem Atualizada!"
                val postagem = Postagem(postagemSelecionada?.id!!,titulo, link, meta, descricao, categoria)
                mainViewModel.updatePostagem(postagem)
            }else{
                salvar = "Postagem Criada!"
                val postagem = Postagem(0,titulo, link, meta, descricao, categoria)
                mainViewModel.addPostagem(postagem)
            }
            Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_postagemFragment_to_feedFragment)

        }else{
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemSelecionada
        if (postagemSelecionada != null){
            binding.TextTitulo.setText(postagemSelecionada?.titulo)
            binding.textDescricao.setText(postagemSelecionada?.descricao)
            binding.textLink.setText(postagemSelecionada?.imagem)
            binding.textMeta.setText(postagemSelecionada?.meta)
        }
    }


}