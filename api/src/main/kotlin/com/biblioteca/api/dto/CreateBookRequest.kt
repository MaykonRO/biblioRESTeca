package com.biblioteca.api.dto

import com.biblioteca.api.model.Category
import com.biblioteca.api.model.BookStatus
import java.time.LocalDateTime

data class CreateBookRequest(
    val title: String,
    val author: String,
    val isbn: String?,
    val category: Category,
)