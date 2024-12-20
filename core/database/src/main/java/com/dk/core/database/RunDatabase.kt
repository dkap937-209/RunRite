package com.dk.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dk.core.database.dao.AnalyticsDao
import com.dk.core.database.dao.RunDao
import com.dk.core.database.dao.RunPendingSyncDao
import com.dk.core.database.entity.DeletedRunSyncEntity
import com.dk.core.database.entity.RunEntity
import com.dk.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase: RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
    abstract val analyticsDao: AnalyticsDao
}