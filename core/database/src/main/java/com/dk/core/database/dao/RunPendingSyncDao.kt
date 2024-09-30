package com.dk.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dk.core.database.entity.DeletedRunSyncEntity
import com.dk.core.database.entity.RunPendingSyncEntity

@Dao
interface RunPendingSyncDao {

    // Created Runs

    @Query("SELECT * FROM runpendingsyncentity WHERE userId=:userId")
    suspend fun getAllRunPendingSyncEntities(userId: String): List<RunPendingSyncEntity>

    @Query("SELECT * FROM runpendingsyncentity WHERE runId=:runId")
    suspend fun getRunPendingSyncEntity(runId: String): RunPendingSyncEntity?

    @Upsert
    suspend fun upsertRunPendingSyncEntity(runPendingSyncEntity: RunPendingSyncEntity)

    @Query("DELETE FROM runpendingsyncentity WHERE runId=:runId")
    suspend fun deleteRunPendingSyncEntity(runId: String)

    // Deleted Runs

    @Query("SELECT * FROM deletedrunsyncentity WHERE userId=:userId")
    suspend fun getAllDeletedRunSyncEntities(userId: String): List<DeletedRunSyncEntity>

    @Upsert
    suspend fun upsertDeletedRunSyncEntity(entity: DeletedRunSyncEntity)

    @Query("DELETE FROM deletedrunsyncentity WHERE runId=:runId")
    suspend fun deleteDeletedRunSyncEntity(runId: String)
}