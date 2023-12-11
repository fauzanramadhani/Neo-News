package com.fgr.neonews.data.listener

interface ApiListener<T : Any> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
}