package com.example.prototipo_app.model

data class Postagem (
    var titulo: String,
    var imagem: String,
    var meta: String,
    var descricao: String,
    var tema: Categoria
){ }