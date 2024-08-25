@file:OptIn(ExperimentalMaterial3Api::class)

package com.dk.run.presentation.active_run

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dk.core.presentation.designsystem.RunRiteTheme
import com.dk.core.presentation.designsystem.StartIcon
import com.dk.core.presentation.designsystem.StopIcon
import com.dk.core.presentation.designsystem.components.RunRiteFloatingActionButton
import com.dk.core.presentation.designsystem.components.RunRiteScaffold
import com.dk.core.presentation.designsystem.components.RunRiteToolBar
import com.dk.run.presentation.R
import com.dk.run.presentation.active_run.components.RunDataCard
import org.koin.androidx.compose.koinViewModel

@Composable

fun ActiveRunScreenRoot(
    viewModel: ActiveRunViewModel = koinViewModel()
) {
    ActiveRunScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ActiveRunScreen(
    state: ActiveRunState,
    onAction: (ActiveRunAction) -> Unit
) {
    RunRiteScaffold(
        withGradient = false,
        topAppBar = {
            RunRiteToolBar(
                showBackButton = true,
                title = stringResource(id = R.string.active_run),
                onBackClick = {
                    onAction(ActiveRunAction.OnBackClick)
                }
            )
        },
        floatingActionButton = {
            RunRiteFloatingActionButton(
                icon = if(state.shouldTrack) {
                    StopIcon
                } else StartIcon,
                onClick = {
                    onAction(ActiveRunAction.OnToggleRunClick)
                },
                iconSize = 20.dp,
                contentDescription = if(state.shouldTrack) {
                    stringResource(id = R.string.pause_run)
                } else stringResource(id = R.string.start_run)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            RunDataCard(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                elapsedTime = state.elapsedTime,
                runData = state.runData
            )
        }
    }
}

@Preview
@Composable
private fun ActiveRunScreenPreview() {
    RunRiteTheme {
        ActiveRunScreen(
            state = ActiveRunState(),
            onAction = {}
        )
    }
}