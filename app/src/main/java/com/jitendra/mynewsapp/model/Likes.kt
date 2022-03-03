package com.jitendra.mynewsapp.model

import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("likes"       ) var count       : Int,
)
