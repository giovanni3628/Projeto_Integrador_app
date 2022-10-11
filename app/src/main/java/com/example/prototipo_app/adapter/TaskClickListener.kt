package com.example.prototipo_app.adapter

import com.example.prototipo_app.model.Postagem

interface TaskClickListener {

    fun onTaskClickListener(postagem: Postagem)

    fun onDetailClick(postagem: Postagem)

    fun onClickShare()
}