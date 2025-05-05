package com.vedatakcan.todoapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vedatakcan.todoapp.data.database.TodoDatabase
import com.vedatakcan.todoapp.data.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    // Migration 1'den 2'ye geçiş için tanımlama
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Örneğin, yeni bir sütun ekliyoruz
            db.execSQL("ALTER TABLE todos ADD COLUMN createdAt INTEGER DEFAULT 0 NOT NULL")
        }
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "todos"
        )
            // Migration'ı burada ekliyoruz
            .addMigrations(MIGRATION_1_2) // Veritabanı sürümü 1'den 2'ye geçiş
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDatabase: TodoDatabase): TodoRepository {
        return TodoRepository(todoDatabase.todoDao())
    }
}
