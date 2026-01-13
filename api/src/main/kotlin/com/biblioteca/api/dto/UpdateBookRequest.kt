package com.biblioteca.api.dto

import com.biblioteca.api.model.BookStatus

data class UpdateBookRequest(
    var status: String
)
