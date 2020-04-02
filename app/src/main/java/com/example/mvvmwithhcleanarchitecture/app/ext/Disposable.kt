package com.example.mvvmwithhcleanarchitecture.app.ext

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


@JvmName("addToComposite")
fun Disposable.addTo(disposable: CompositeDisposable) {
    disposable.add(this)
}