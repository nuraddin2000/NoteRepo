package com.test.notesapp.di

import android.content.Context
import androidx.room.Room
import com.test.notesapp.repo.NoteRepository
import com.test.notesapp.repo.NoteRepositoryInterface
import com.test.notesapp.roomdb.NoteDao
import com.test.notesapp.roomdb.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,NoteDatabase::class.java,"NoteDB").build()

    @Singleton
    @Provides
    fun injectDao(
        database: NoteDatabase
    ) = database.noteDao()

    @Singleton
    @Provides
    fun injectNormalRepo(dao : NoteDao) = NoteRepository(dao) as NoteRepositoryInterface
}