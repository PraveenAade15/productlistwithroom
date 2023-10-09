package com.cheezycode.notesample

import android.app.Application
import com.cheezycode.notesample.database.NoteDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application(){
//    val noteDatabase by lazy {
//        NoteDatabase.getDatabase(this)
//    }
}
