package com.mingui.dallija.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mingui.dallija.data.dao.LocalRecordDatabase
import com.mingui.dallija.data.repository.AutoLoginRepository
import com.mingui.dallija.data.repository.RecordRepositoryImpl
import com.mingui.dallija.domain.repository.RecordRepository
import com.mingui.dallija.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecordeDatabase(app: Application) : LocalRecordDatabase{
        return Room.databaseBuilder(
            app,
            LocalRecordDatabase::class.java,
            LocalRecordDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecordRepository(db : LocalRecordDatabase): RecordRepository{
        return RecordRepositoryImpl(db.recordDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: RecordRepository) : RecordUseCases {
        return RecordUseCases(
            getRecords = GetRecords(repository),
            deleteRecord = DeleteRecord(repository),
            addRecord =  AddRecord(repository),
            getRecord = GetRecord(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAutoLoginRepository(
        @ApplicationContext context: Context
    ) = AutoLoginRepository(context)


}