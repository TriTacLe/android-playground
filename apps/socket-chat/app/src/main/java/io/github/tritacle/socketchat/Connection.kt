package io.github.tritacle.socketchat

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Connection(private val socket: Socket) {

    // The charset is stated in both directions. Without it each side falls back to
    // its own platform default, and the Norwegian letters arrive wrong at a peer
    // that defaults to something else.
    private val reader = BufferedReader(
        InputStreamReader(socket.getInputStream(), Charsets.UTF_8)
    )
    private val writer = BufferedWriter(
        OutputStreamWriter(socket.getOutputStream(), Charsets.UTF_8)
    )

    suspend fun send(message: String) = withContext(Dispatchers.IO) {
        writer.write(message)
        writer.newLine()
        writer.flush()
    }

    /** Gir null når motparten har lukket forbindelsen. */
    suspend fun receive(): String? = withContext(Dispatchers.IO) {
        reader.readLine()
    }

    fun close() {
        socket.close()
    }
}
