package com.magdalena.movietoday.api.movie


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: MutableList<Result>,
    @SerializedName("page")
    val page: Long,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("total_pages")
    val totalPages: Long
)