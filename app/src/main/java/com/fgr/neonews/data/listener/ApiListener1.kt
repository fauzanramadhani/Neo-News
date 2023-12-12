package com.fgr.neonews.data.listener

interface CallbackListener<T : Any> {
    fun onSuccess(data: T)
    fun onFailure(message: String)
}