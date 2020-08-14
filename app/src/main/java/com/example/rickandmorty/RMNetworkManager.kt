package com.example.rickandmorty

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RMNetworkManager {

    private val client = OkHttpClient()

    fun getLocations(): Response {
        val request = Request.Builder()
            .url("https://rickandmortyapi.com/api/location/")
            .build()

        return client.newCall(request).execute()
    }
}

data class LocationResultInfo(val count: Int, val pages: Int, val next: String, val prev: String)
data class Location(val id: Int, val name: String, val type: String, val dimension: String, val residents: Array<String>, val url: String, val created: String)
data class LocationResult(val info: LocationResultInfo, val results: Array<Location>)