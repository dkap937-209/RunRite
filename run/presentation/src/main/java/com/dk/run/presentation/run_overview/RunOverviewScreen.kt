@file:OptIn(ExperimentalMaterial3Api::class)

package com.dk.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dk.core.presentation.designsystem.AnalyticsIcon
import com.dk.core.presentation.designsystem.LogoIcon
import com.dk.core.presentation.designsystem.LogoutIcon
import com.dk.core.presentation.designsystem.RunIcon
import com.dk.core.presentation.designsystem.RunRiteTheme
import com.dk.core.presentation.designsystem.components.RunRiteFloatingActionButton
import com.dk.core.presentation.designsystem.components.RunRiteScaffold
import com.dk.core.presentation.designsystem.components.RunRiteToolBar
import com.dk.core.presentation.designsystem.components.util.DropDownItem
import com.dk.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    viewModel: RunOverviewViewModel = koinViewModel()

) {
    RunOverviewScreen(
        onAction = { action ->
            when(action){
                RunOverviewAction.OnStartClick -> onStartRunClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
private fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    RunRiteScaffold(
        topAppBar = {
            RunRiteToolBar(
                showBackButton = false,
                title = stringResource(id = R.string.runrite),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    )
                ),
                onMenuItemClick = { index ->
                    when(index){
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            RunRiteFloatingActionButton(
                icon = RunIcon,
                onClick = {
                    onAction(RunOverviewAction.OnStartClick)
                }
            )
        }
    ) { padding ->

    }

}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
     RunRiteTheme{
        RunOverviewScreen(
            onAction = {}
        )
    }
}