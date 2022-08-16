package com.mingui.dallija.domain.model

data class User(
    val _id : Int,
    val name: String,
    val email:String,
    val sex : Int,
    val height : Int,
    val profile_photo_path : String,
)
