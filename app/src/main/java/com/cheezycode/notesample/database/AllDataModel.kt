package com.cheezycode.notesample.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName = "note_table")
data class AllDataModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "price")
    val price: Double? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "category")
    val category: String? = null,

    @ColumnInfo(name = "image")
    val image: String? = null,

    @ColumnInfo(name = "rating_rate")
    val ratingRate: Double? = null,

    @ColumnInfo(name = "rating_count")
    val ratingCount: Int? = null
)

//
//@Entity(tableName = "note_table")
//data class AllDataModel(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int? = null,
//    val title: String? = null,
//    val price: Double? = null,
//    val description: String? = null,
//    val category: String? = null,
//    val image: String? = null,
//    val rating: Double? = null, // You can store the "rate" field from Rating directly
//    val ratingCount: Int? = null // You can store the "count" field from Rating directly
//)
