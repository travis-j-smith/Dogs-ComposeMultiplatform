package com.iamtravisjsmith.dogs

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class DogApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun fetchDogs(): List<String> {
        val imageUrls = client.get(apiUrl).body<List<String>>()
        return imageUrls
            .filter { imageUrl ->
                val fileType = imageUrl.split('.').lastOrNull()
                fileType != null && supportedFileTypes.contains(fileType.lowercase())
            }.map { imageUrl ->
                urlPrefix + imageUrl
            }
    }

    companion object {
        private const val apiUrl = "https://random.dog/doggos"
        private const val urlPrefix = "https://random.dog/"
        private val supportedFileTypes: List<String> = listOf(
            "jpg",
            "jpeg",
            "png",
        )
    }
}
