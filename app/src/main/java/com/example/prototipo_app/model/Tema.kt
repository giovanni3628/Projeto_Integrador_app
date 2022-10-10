package com.example.prototipo_app.model

 data class Tema (
    var id: Long,
    var tema: String?,
    var descricao: String?,
    var postagem: List<Postagem>?
        ){

     override fun toString(): String {
         return tema!!
     }

}