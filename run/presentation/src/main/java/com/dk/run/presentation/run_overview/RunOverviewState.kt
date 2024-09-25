package com.dk.run.presentation.run_overview

import com.dk.run.presentation.run_overview.model.RunUi

data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)
