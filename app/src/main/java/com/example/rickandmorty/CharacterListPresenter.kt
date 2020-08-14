package com.example.rickandmorty
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Response
import java.lang.reflect.Type


interface CharacterListView {
    fun updateList(data: Array<CharacterResult>)
    fun showToast(msg: String)
}

class CharacterListPresenter {

    private val networkManager = RMNetworkManager()
    private var view: CharacterListView? = null
    private val subscriptions = CompositeDisposable()

    fun subscribe(view: CharacterListView) {
        this.view = view
    }

    fun unsubscribe() {
        this.view = null
    }

    fun fetchCharacters(urlArray: Array<String>) {
        val listType: Type = object : TypeToken<ArrayList<CharacterResult?>?>() {}.type
        subscriptions.add(Single.create<Response> { emitter ->
            emitter.onSuccess(networkManager.getMultipleCharacters(urlArray.map {
                it.removePrefix("https://rickandmortyapi.com/api/character/")
            }.toTypedArray()))
        }.map { Gson().fromJson<List<CharacterResult>>(it.body?.string(), listType) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: List<CharacterResult> ->
                view?.updateList(data.toTypedArray())
            }) { e: Throwable ->
                view?.showToast(e.localizedMessage ?: "null")
            })

    }
}