package io.github.tritacle.friendeditor

import kotlinx.serialization.Serializable

@Serializable
object FriendList

@Serializable
object AddFriend

@Serializable
data class FriendDetail(val index: Int)

@Serializable
data class EditFriend(val index: Int)
