package no.ntnu.idatt2506.numbergame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val GAME_URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class GameViewModel : ViewModel() {

    // One instance for the life of the ViewModel, so the session survives rotation.
    private val network = HttpWrapper(GAME_URL)

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _guess = MutableStateFlow("")
    val guess: StateFlow<String> = _guess.asStateFlow()

    private val _messages = MutableStateFlow(listOf<String>())
    val messages: StateFlow<List<String>> = _messages.asStateFlow()

    private val _registered = MutableStateFlow(false)
    val registered: StateFlow<Boolean> = _registered.asStateFlow()

    fun changeName(value: String) {
        _name.value = value
    }

    fun changeCardNumber(value: String) {
        _cardNumber.value = value
    }

    fun changeGuess(value: String) {
        _guess.value = value
    }

    fun startGame() {
        _registered.value = true
        send(mapOf("navn" to _name.value, "kortnummer" to _cardNumber.value))
    }

    fun sendGuess() {
        send(mapOf("tall" to _guess.value))
        _guess.value = ""
    }

    fun newGame() {
        _registered.value = false
        _messages.value = listOf()
        _guess.value = ""
    }

    private fun send(parameters: Map<String, String>) {
        viewModelScope.launch {
            val message = try {
                network.get(parameters).trim()
            } catch (e: IOException) {
                e.toString()
            }
            _messages.value = _messages.value + message
        }
    }
}
