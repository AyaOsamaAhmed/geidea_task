package com.aya.geidea_task.data.remote.client

import com.aya.geidea_task.utils.Constants.BASE_URL_API
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


object KtorClient {

    private const val AUTHORIZATION_HEADER = "Authorization"
    private var API_KEY: String = ""



    private val client = HttpClient(Android) {
        defaultRequest {
            host = BASE_URL_API
            url {
                protocol = URLProtocol.HTTPS
            }
            header(AUTHORIZATION_HEADER, "BEARER $API_KEY")
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
        }
    }

    val getInstance = client
}