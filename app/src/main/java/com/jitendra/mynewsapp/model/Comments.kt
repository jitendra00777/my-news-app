package com.jitendra.mynewsapp.model

import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("comments"       ) var count       : Int,
)
