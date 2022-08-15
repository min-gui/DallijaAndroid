package com.mingui.dallija.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    val startTimeStamp : Long,
    val distance : Long,
    val runface : Long,
    val runtime : Long,
    val kal : Int,
    val content : String,
    @PrimaryKey val id: Int? = null
){
    companion object {
        val kalRange = listOf(100,200,300,400)
    }
}

class InvalidRecordException(message: String) : Exception(message )
