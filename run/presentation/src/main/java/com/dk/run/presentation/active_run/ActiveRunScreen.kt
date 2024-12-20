@file:OptIn(ExperimentalMaterial3Api::class)

package com.dk.run.presentation.active_run

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dk.core.presentation.designsystem.RunRiteTheme
import com.dk.core.presentation.designsystem.StartIcon
import com.dk.core.presentation.designsystem.StopIcon
import com.dk.core.presentation.designsystem.components.RunRiteActionButton
import com.dk.core.presentation.designsystem.components.RunRiteDialog
import com.dk.core.presentation.designsystem.components.RunRiteFloatingActionButton
import com.dk.core.presentation.designsystem.components.RunRiteOutlinedActionButton
import com.dk.core.presentation.designsystem.components.RunRiteScaffold
import com.dk.core.presentation.designsystem.components.RunRiteToolBar
import com.dk.core.presentation.ui.ObserveAsEvents
import com.dk.run.presentation.R
import com.dk.run.presentation.active_run.components.RunDataCard
import com.dk.run.presentation.active_run.maps.TrackerMap
import com.dk.core.notification.ActiveRunService
import com.dk.run.presentation.util.hasLocationPermission
import com.dk.run.presentation.util.hasNotificationPermission
import com.dk.run.presentation.util.shouldShowLocationPermissionRationale
import com.dk.run.presentation.util.shouldShowNotificationPermissionRationale
import org.koin.androidx.compose.koinViewModel
import java.io.ByteArrayOutputStream

@Composable

fun ActiveRunScreenRoot(
    onFinish: () -> Unit,
    onBack: () -> Unit,
    onServiceToggle: (isServiceRunning: Boolean) -> Unit,
    viewModel: ActiveRunViewModel = koinViewModel()
) {
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when(event) {
            is ActiveRunEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            ActiveRunEvent.RunSaved -> onFinish()
        }
    }
    ActiveRunScreen(
        state = viewModel.state,
        onServiceToggle = onServiceToggle,
        onAction = { action ->
            when(action) {
                is ActiveRunAction.OnBackClick -> {
                    if(!viewModel.state.hasStartedRunning) {
                        onBack()
                    }
                }
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun ActiveRunScreen(
    state: ActiveRunState,
    onServiceToggle: (isServiceRunning: Boolean) -> Unit,
    onAction: (ActiveRunAction) -> Unit
) {

    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val hasCoarseLocationPermission = perms[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        val hasFineLocationPermission = perms[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val hasPostNotificationPermission = if(Build.VERSION.SDK_INT >= 33){
            perms[Manifest.permission.POST_NOTIFICATIONS] == true
        } else true

        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = hasFineLocationPermission && hasCoarseLocationPermission,
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = hasPostNotificationPermission,
                showNotificationPermissionRationale = showNotificationRationale
            )
        )
    }

    LaunchedEffect(key1 = true) {
        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = context.hasLocationPermission(),
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = context.hasNotificationPermission(),
                showNotificationPermissionRationale = showNotificationRationale
            )
        )

        if(!showLocationRationale && !showNotificationRationale){
            permissionLauncher.requestRunRitePermissions(context)
        }
    }

    LaunchedEffect(key1 = state.isRunFinished) {
        if(state.isRunFinished){
            onServiceToggle(false)
        }
    }

    val isServiceActive by ActiveRunService.isServiceActive.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = state.shouldTrack, isServiceActive) {
        if(context.hasLocationPermission() && state.shouldTrack && !isServiceActive) {
            onServiceToggle(true)
        }
    }

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
            TrackerMap(
                modifier = Modifier
                    .fillMaxSize(),
                isRunFinished = state.isRunFinished,
                currentLocation = state.currentLocation,
                locations = state.runData.locations,
                onSnapshot = { bmp ->
                    val stream = ByteArrayOutputStream()
                    stream.use {
                        bmp.compress(
                            Bitmap.CompressFormat.JPEG,
                            80,
                            it
                        )
                    }
                    onAction(ActiveRunAction.OnRunProcessed(stream.toByteArray()))
                },
            )
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

    if(!state.shouldTrack && state.hasStartedRunning) {
        RunRiteDialog(
            title = stringResource(id = R.string.running_is_paused),
            onDismiss = {
                onAction(ActiveRunAction.OnResumeClick)
            },
            description = stringResource(id = R.string.resume_or_finish_run),
            primaryButton = {
              RunRiteActionButton(
                  text = stringResource(id = R.string.resume),
                  isLoading = false,
                  onClick = {
                      onAction(ActiveRunAction.OnResumeClick)
                  },
                  modifier = Modifier
                      .weight(1f)
              )
            },
            secondaryButton = {
                RunRiteOutlinedActionButton(
                    text = stringResource(id = R.string.finish),
                    isLoading = state.isSavingRun,
                    onClick = {
                        onAction(ActiveRunAction.OnFinishRunClick)
                    },
                    modifier = Modifier
                        .weight(1f)
                )
            }
        )
    }

    if(state.showLocationRationale || state.showNotificationRationale) {
        RunRiteDialog(
            title = stringResource(id = R.string.permission_required),
            onDismiss = { /* Normal dismissing not allowed for permissions*/ },
            description = when{
                state.showLocationRationale && state.showNotificationRationale -> {
                    stringResource(id = R.string.location_notification_rationale)
                }
                state.showLocationRationale -> {
                    stringResource(id = R.string.location_rationale)
                }
                else-> {
                    stringResource(id = R.string.notification_rationale)
                }
            },
            primaryButton = {
                RunRiteOutlinedActionButton(
                    text = stringResource(id = R.string.okay),
                    isLoading = false,
                    onClick = {
                        onAction(ActiveRunAction.DismissRationaleDialog)
                        permissionLauncher.requestRunRitePermissions(context)
                    }
                )
            }
        )
    }
}

private fun ActivityResultLauncher<Array<String>>.requestRunRitePermissions(
    context: Context
){
    val hasLocationPermissions = context.hasLocationPermission()
    val hasNotificationPermission = context.hasNotificationPermission()

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val notificationPermission = if(Build.VERSION.SDK_INT >= 33){
        arrayOf(Manifest.permission.POST_NOTIFICATIONS)
    } else arrayOf()

    when {
        !hasLocationPermissions && !hasNotificationPermission -> {
            launch(locationPermissions + notificationPermission)
        }
        !hasLocationPermissions -> launch(locationPermissions)
        !hasNotificationPermission -> launch(notificationPermission)
    }
}

@Preview
@Composable
private fun ActiveRunScreenPreview() {
    RunRiteTheme {
        ActiveRunScreen(
            state = ActiveRunState(),
            onAction = {},
            onServiceToggle = {}
        )
    }
}