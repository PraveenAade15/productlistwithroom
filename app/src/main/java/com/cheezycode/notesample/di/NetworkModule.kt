package com.cheezycode.notesample.di

import android.content.Context
import com.cheezycode.notesample.api.NoteAPI
import com.cheezycode.notesample.database.NoteDatabase
import com.cheezycode.notesample.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

//    @Singleton
//    @Provides`
//    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
//        return OkHttpClient.Builder().addInterceptor(interceptor).build()
//    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder): NoteAPI {
        return retrofitBuilder.build().create(NoteAPI::class.java)
    }

//    @Singleton
//    @Provides
//    fun providesNoteAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): NoteAPI {
//        return retrofitBuilder.client(okHttpClient).build().create(NoteAPI::class.java)
//    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return NoteDatabase.getDatabase(context)
    }

//    @Singleton
//    @Provides
//    fun provideDatabase(
//        @ApplicationContext context: Context
//    ) = Room.databaseBuilder(
//        context,
//        NoteDatabase::class.java,
//        "note_table"
//    ).build()

    @Singleton
    @Provides
    fun provideDao(database: NoteDatabase) = database.noteDao()


}