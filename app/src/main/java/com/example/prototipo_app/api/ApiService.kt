package com.example.prototipo_app.api

import com.example.prototipo_app.model.Categoria
import com.example.prototipo_app.model.Postagem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("tema")
    suspend fun listCategoria(): Response<List<Categoria>>

    @GET("postagem")
    suspend fun listPostagem(): Response<List<Postagem>>

    @POST("postagem")
    suspend fun addPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>
}