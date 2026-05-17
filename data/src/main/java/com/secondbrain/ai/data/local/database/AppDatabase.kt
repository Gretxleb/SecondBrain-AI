package com.secondbrain.ai.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.secondbrain.ai.data.local.dao.NoteDao
import com.secondbrain.ai.data.local.dao.ReminderDao
import com.secondbrain.ai.data.local.dao.SettingsDao
import com.secondbrain.ai.data.local.dao.VoiceMemoDao
import com.secondbrain.ai.data.local.entity.NoteEntity
import com.secondbrain.ai.data.local.entity.ReminderEntity
import com.secondbrain.ai.data.local.entity.SettingsEntity
import com.secondbrain.ai.data.local.entity.VoiceMemoEntity

@Database(
    entities = [NoteEntity::class, SettingsEntity::class, ReminderEntity::class, VoiceMemoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun settingsDao(): SettingsDao
    abstract fun reminderDao(): ReminderDao
    abstract fun voiceMemoDao(): VoiceMemoDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 1) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS settings (id INTEGER PRIMARY KEY NOT NULL, darkModeEnabled INTEGER NOT NULL, language TEXT NOT NULL, biometricEnabled INTEGER NOT NULL, syncEnabled INTEGER NOT NULL)")
            }
        }
    }
}
