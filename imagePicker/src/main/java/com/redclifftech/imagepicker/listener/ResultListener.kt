package com.redclifftech.imagepicker.listener

internal interface ResultListener<T> {

    fun onResult(t: T?)
}
