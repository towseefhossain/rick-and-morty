package com.example.rickandmorty

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RMNetworkManager {

    private val client = OkHttpClient()
    private val baseURL = "https://rickandmortyapi.com/api"

    fun getLocations(): Response {
        val request = Request.Builder()
            .url("${baseURL}/location/")
            .build()

        return client.newCall(request).execute()
    }

    fun getMultipleCharacters(characterArray: Array<String>): Response {
        val request = Request.Builder()
            .url("${baseURL}/character/${characterArray.joinToString()}")
            .build()

        return client.newCall(request).execute()
    }
}

data class LocationResultInfo(val count: Int, val pages: Int, val next: String, val prev: String)
data class Location(val id: Int, val name: String, val type: String, val dimension: String, val residents: Array<String>, val url: String, val created: String)
data class LocationResult(val info: LocationResultInfo, val results: Array<Location>)

data class CharacterResult(val name: String, val status: String, val species: String, val type: String, val gender: String, val image: String, val origin: OriginOrLocation, val location: OriginOrLocation)
data class OriginOrLocation(val name: String)