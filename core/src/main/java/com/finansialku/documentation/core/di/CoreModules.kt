package com.finansialku.documentation.core.di

import androidx.room.Room
import com.finansialku.documentation.core.data.database.UniversityDatabase
import com.finansialku.documentation.core.data.rest.ApiInterfaces
import com.finansialku.documentation.core.data.rest.UniversityRepository
import com.finansialku.documentation.core.presentation.UniversityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreModules = module {
    single {
        ApiInterfaces(androidContext())
    }
    single {
        UniversityRepository(get(), get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            UniversityDatabase::class.java, UniversityDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<UniversityDatabase>().getUniversity() }
    viewModel { UniversityViewModel(get()) }
}
