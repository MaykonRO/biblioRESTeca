package com.biblioteca.api.dto

import com.biblioteca.api.model.Category
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateBookRequest(
    @field:NotBlank(message = "Título é obrigatório")
    @field:Size(min = 3, message = "Título deve ter no mínimo 3 caracteres")
    val title: String,

    @field:NotBlank(message = "Autor é obrigatório")
    @field:Size(min = 3, message = "Autor deve ter no mínimo 3 caracteres")
    val author: String,

    @field:Pattern(regexp = "\\d{13}", message = "ISBN deve ter exatamente 13 caracteres")
    val isbn: String?,
    val category: String
)