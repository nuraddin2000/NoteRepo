package com.test.notesapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    var title : String? = null,
    var body: String? = null
)
