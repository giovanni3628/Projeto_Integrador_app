package com.example.prototipo_app.model

 data class Categoria (
    var id: Long,
    var tema: String?,
    var descricao: String?,
    var postagem: List<Postagem>?
        ){

     override fun toString(): String {
         return tema!!
     }

}