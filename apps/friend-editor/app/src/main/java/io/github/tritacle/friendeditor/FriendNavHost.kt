package io.github.tritacle.friendeditor

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun FriendNavHost(
    modifier: Modifier = Modifier,
    viewModel: FriendViewModel = viewModel()
) {
    val nav = rememberNavController()
    val friends by viewModel.friends.collectAsStateWithLifecycle()

    NavHost(nav, startDestination = FriendList, modifier = modifier) {
        composable<FriendList> {
            FriendListScreen(
                friends = friends,
                onFriendClick = { index -> nav.navigate(FriendDetail(index)) },
                onAddClick = {
                    viewModel.clearForm()
                    nav.navigate(AddFriend)
                }
            )
        }
        composable<AddFriend> {
            FriendFormRoute(
                viewModel = viewModel,
                titleRes = R.string.new_friend,
                buttonRes = R.string.add_friend,
                onSubmit = {
                    viewModel.addFriend()
                    nav.popBackStack()
                }
            )
        }
        composable<FriendDetail> { entry ->
            val route: FriendDetail = entry.toRoute()
            val friend = friends.getOrNull(route.index)
            if (friend != null) {
                FriendDetailScreen(
                    friend = friend,
                    onEdit = {
                        viewModel.loadFriendIntoForm(route.index)
                        nav.navigate(EditFriend(route.index))
                    },
                    onBack = { nav.popBackStack() }
                )
            }
        }
        composable<EditFriend> { entry ->
            val route: EditFriend = entry.toRoute()
            FriendFormRoute(
                viewModel = viewModel,
                titleRes = R.string.edit_friend,
                buttonRes = R.string.save_friend,
                onSubmit = {
                    viewModel.replaceFriend(route.index)
                    nav.popBackStack()
                }
            )
        }
    }
}

@Composable
private fun FriendFormRoute(
    viewModel: FriendViewModel,
    @StringRes titleRes: Int,
    @StringRes buttonRes: Int,
    onSubmit: () -> Unit
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val birthday by viewModel.birthday.collectAsStateWithLifecycle()

    FriendForm(
        title = stringResource(titleRes),
        buttonLabel = stringResource(buttonRes),
        name = name,
        birthday = birthday,
        onNameChange = { viewModel.changeName(it) },
        onBirthdayChange = { viewModel.changeBirthday(it) },
        onSubmit = onSubmit
    )
}
