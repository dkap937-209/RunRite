package com.dk.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.dk.core.database.dao.RunPendingSyncDao
import com.dk.core.domain.run.RemoteRunDataSource
import com.dk.core.domain.util.Result

class DeleteRunWorker(
    context: Context,
    private val params: WorkerParameters,
    private val pendingSyncDao: RunPendingSyncDao,
    private val remoteRunDataSource: RemoteRunDataSource
): CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if(runAttemptCount >= 5) {
            return Result.failure()
        }

        val runId = params.inputData.getString(RUN_ID) ?: return Result.failure()
        return when(val result = remoteRunDataSource.deleteRun(runId)){
            is com.dk.core.domain.util.Result.Error -> {
                result.error.toWorkerResult()
            }
            is com.dk.core.domain.util.Result.Success -> {
                pendingSyncDao.deleteDeletedRunSyncEntity(runId)
                Result.success()
            }
        }

    }

    companion object {
        const val RUN_ID = "RUN_ID"
    }
}