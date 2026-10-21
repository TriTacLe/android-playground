package io.github.tritacle.numbergame

import java.net.CookieHandler
import java.net.CookieManager
import java.net.URL
import java.net.URLEncoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HttpWrapper(private val url: String) {
    init {
        CookieHandler.setDefault(CookieManager())
    }

    suspend fun get(parameters: Map<String, String>): String =
        withContext(Dispatchers.IO) {
            val query = encodeParameters(parameters)
            val connection = URL("$url?$query").openConnection()
            connection.inputStream.reader().use { it.readText() }
        }
}

fun encodeParameters(parameters: Map<String, String>): String =
    parameters.entries.joinToString("&") { entry ->
        encode(entry.key) + "=" + encode(entry.value)
    }

private fun encode(text: String): String =
    URLEncoder.encode(text, "UTF-8")
