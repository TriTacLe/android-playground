package no.ntnu.idatt2506.friendsnav

import kotlinx.serialization.Serializable

// Each destination is a type. The two that need to know which friend carry its position in
// the list, not its name: the name can be edited, and a route built from it would then point
// at a friend that no longer exists. The position holds because the list only grows at the end.
@Serializable
object FriendList

@Serializable
object AddFriend

@Serializable
data class FriendDetail(val index: Int)

@Serializable
data class EditFriend(val index: Int)
