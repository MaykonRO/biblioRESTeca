package com.biblioteca.api.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Book(
    val tittle: String = "",
    val author: String = "",
    val isbn: Long? = null,
    @Enumerated(EnumType.STRING)
    val category: Category = Category.OTHER,
    @Enumerated(EnumType.STRING)
    val status: BookStatus = BookStatus.AVAILABLE,
    val registeredAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

}

