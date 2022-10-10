package com.example.prototipo_app.adapter

import com.example.prototipo_app.model.Postagem

interface DetailClickListener {
    fun onTaskClickListener(postagem: Postagem)

    fun onClickShare()
}