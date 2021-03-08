package com.indioalba.playerfinder.repository.service

import javax.inject.Inject

class SearchRequestBodyFactory @Inject constructor() {

    fun createBody(
        query: String,
        searchType: String? = null,
        offset: Int? = null,
        requestOrder: String? = null
    ) = SearchRequestBody(query, searchType, offset, requestOrder)
}