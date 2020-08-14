package com.example.rickandmorty

import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Response


interface LocationListView {
    fun updateList(data: Array<Location>)
    fun showToast(msg: String)
}

class LocationListPresenter {

    private val networkManager = RMNetworkManager()
    private var view: LocationListView? = null
    private val subscriptions = CompositeDisposable()

    fun subscribe(view: LocationListView) {
        this.view = view
    }

    fun unsubscribe() {
        this.view = null
    }

    fun fetchLocations() {
        subscriptions.add(Single.create<Response> { emitter ->
            emitter.onSuccess(networkManager.getLocations())
        }.map { Gson().fromJson(it.body?.string(), LocationResult::class.java) }
            .map { it.results }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: Array<Location> ->
                view?.updateList(data)
            }) { e: Throwable ->
                view?.showToast(e.localizedMessage ?: "null")
            })
    }
}