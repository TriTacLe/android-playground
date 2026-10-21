package io.github.tritacle.socketchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val PORT = 12345

// Verten sett fra innsiden av en emulator. adb forward tar forbindelsen
// det siste steget inn i tjeneremulatoren.
private const val HOST = "10.0.2.2"

enum class Role { NONE, SERVER, CLIENT }

class ChatViewModel : ViewModel() {

    private var server: ChatServer? = null
    private var client: ChatClient? = null

    // Bare rørt fra viewModelScope, som kjører på hovedtråden, så lista
    // trenger ingen beskyttelse.
    private val connections = mutableListOf<Connection>()
    private val labels = mutableMapOf<Connection, String>()
    private var clientConnection: Connection? = null
    private var nextClient = 1

    private val _role = MutableStateFlow(Role.NONE)
    val role: StateFlow<Role> = _role.asStateFlow()

    private val _messages = MutableStateFlow(listOf<String>())
    val messages: StateFlow<List<String>> = _messages.asStateFlow()

    private val _draft = MutableStateFlow("")
    val draft: StateFlow<String> = _draft.asStateFlow()

    private val _connected = MutableStateFlow(0)
    val connected: StateFlow<Int> = _connected.asStateFlow()

    fun changeDraft(value: String) {
        _draft.value = value
    }

    fun startServer() {
        if (_role.value != Role.NONE) return
        _role.value = Role.SERVER
        val listening = ChatServer(PORT)
        server = listening
        viewModelScope.launch {
            try {
                while (true) {
                    handle(Connection(listening.awaitClient()))
                }
            } catch (e: IOException) {
                // ServerSocket er lukket. Det er utgangen fra løkka.
            }
        }
    }

    fun startClient() {
        if (_role.value != Role.NONE) return
        _role.value = Role.CLIENT
        val connecting = ChatClient(HOST, PORT)
        client = connecting
        viewModelScope.launch {
            try {
                val connection = Connection(connecting.connect())
                clientConnection = connection
                _connected.value = 1
                append("Koblet til tjeneren")
                while (true) {
                    append(connection.receive() ?: break)
                }
                append("Tjeneren lukket forbindelsen")
            } catch (e: IOException) {
                append("Mistet forbindelsen: ${e.message}")
            }
            _connected.value = 0
        }
    }

    fun send() {
        val text = _draft.value.trim()
        if (text.isEmpty()) return
        _draft.value = ""
        when (_role.value) {
            Role.SERVER -> {
                val line = "Tjener: $text"
                append(line)
                viewModelScope.launch { sendToOthers(line, null) }
            }
            Role.CLIENT -> {
                append("Du: $text")
                viewModelScope.launch {
                    try {
                        clientConnection?.send(text)
                    } catch (e: IOException) {
                        append("Fikk ikke sendt: ${e.message}")
                    }
                }
            }
            Role.NONE -> return
        }
    }

    private fun handle(connection: Connection) {
        val label = "Klient ${nextClient++}"
        connections.add(connection)
        labels[connection] = label
        _connected.value = connections.size
        viewModelScope.launch {
            announce("$label koblet til", connection)
            try {
                while (true) {
                    val line = connection.receive() ?: break
                    val message = "$label: $line"
                    append(message)
                    sendToOthers(message, connection)
                }
            } catch (e: IOException) {
                // Forbindelsen er lukket.
            }
            connections.remove(connection)
            labels.remove(connection)
            _connected.value = connections.size
            connection.close()
            announce("$label forlot samtalen", connection)
        }
    }

    /** Viser linja hos tjeneren og sender den til alle andre enn [origin]. */
    private suspend fun announce(line: String, origin: Connection?) {
        append(line)
        sendToOthers(line, origin)
    }

    private suspend fun sendToOthers(line: String, sender: Connection?) {
        for (connection in connections.toList()) {
            if (connection === sender) continue
            try {
                connection.send(line)
            } catch (e: IOException) {
                // Denne mottakeren er borte. Lesesløyfa dens rydder opp.
            }
        }
    }

    private fun append(line: String) {
        _messages.value = _messages.value + line
    }

    override fun onCleared() {
        connections.forEach { it.close() }
        clientConnection?.close()
        server?.close()
        client?.close()
    }
}
