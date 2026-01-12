package com.biblioteca.api.dto

import com.biblioteca.api.model.BookStatus
import com.biblioteca.api.model.Category
import java.time.LocalDateTime
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateBookResponse(
    val id: Long,
    val title: String,
    val author: String,
    val isbn: String?,
    val category: Category?,
    val status: BookStatus?,
    val registeredAt: LocalDateTime,
    val updateAt: LocalDateTime? = null
)