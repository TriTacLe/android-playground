package io.github.tritacle.friendeditor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FriendViewModel : ViewModel() {

    private val _friends = MutableStateFlow(listOf<Friend>())
    val friends: StateFlow<List<Friend>> = _friends.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _birthday = MutableStateFlow("")
    val birthday: StateFlow<String> = _birthday.asStateFlow()

    fun changeName(value: String) {
        _name.value = value
    }

    fun changeBirthday(value: String) {
        _birthday.value = value
    }

    fun addFriend() {
        _friends.value = _friends.value + Friend(name = _name.value, birthday = _birthday.value)
        clearForm()
    }

    fun replaceFriend(index: Int) {
        val current = _friends.value
        if (index !in current.indices) return
        _friends.value = current.toMutableList().also {
            it[index] = Friend(name = _name.value, birthday = _birthday.value)
        }
        clearForm()
    }

    fun loadFriendIntoForm(index: Int) {
        val friend = _friends.value.getOrNull(index) ?: return
        _name.value = friend.name
        _birthday.value = friend.birthday
    }

    fun clearForm() {
        _name.value = ""
        _birthday.value = ""
    }
}
