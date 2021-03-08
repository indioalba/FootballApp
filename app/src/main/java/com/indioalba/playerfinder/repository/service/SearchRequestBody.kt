package com.indioalba.playerfinder.repository.service

data class SearchRequestBody(
    val searchString: String,
    val searchType: String?,
    val offset: Int?,
    val requestOrder: String?
)