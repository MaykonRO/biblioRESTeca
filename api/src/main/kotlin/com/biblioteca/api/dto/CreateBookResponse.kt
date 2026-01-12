package com.biblioteca.api.dto

import com.biblioteca.api.model.BookStatus
import com.biblioteca.api.model.Category
import java.time.LocalDateTime

data class CreateBookResponse(
    val id: Long,
    val title: String,
    val author: String,
    val isbn: String?,
    val category: Category?,
    val status: BookStatus?,
    val registeredAt: LocalDateTime
)