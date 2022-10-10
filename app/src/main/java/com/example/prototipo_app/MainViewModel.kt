package com.example.prototipo_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prototipo_app.api.Repository
import com.example.prototipo_app.model.Tema
import com.example.prototipo_app.model.Postagem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    var postagemSelecionada: Postagem? = null

    private val _myCategoriaResponse =
        MutableLiveData<Response<List<Tema>>>()

    val myCategoriaResponse : LiveData<Response<List<Tema>>> =
        _myCategoriaResponse

    private val _myPostagemResponse =
        MutableLiveData<Response<List<Postagem>>>()

    val myPostagemResponse : LiveData<Response<List<Postagem>>> =
        _myPostagemResponse

    fun addPostagem( postagem: Postagem){
        viewModelScope.launch {
            try {
                repository.addPostagem(postagem)
                listPostagem()
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listPostagem(){
        viewModelScope.launch {
            try {
                val response = repository.listPostagem()
                _myPostagemResponse.value = response

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listCategoria(){
        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _myCategoriaResponse.value = response

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updatePostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
                repository.updatePostagem(postagem)
                listPostagem()
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

}