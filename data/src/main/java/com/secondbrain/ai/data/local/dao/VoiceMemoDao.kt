package com.secondbrain.ai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.secondbrain.ai.data.local.entity.VoiceMemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VoiceMemoDao {
    @Query("SELECT * FROM voice_memos ORDER BY createdAt DESC")
    fun streamVoiceMemos(): Flow<List<VoiceMemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(voiceMemo: VoiceMemoEntity)
}
