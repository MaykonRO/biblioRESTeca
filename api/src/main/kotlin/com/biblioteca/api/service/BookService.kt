package com.biblioteca.api.service

//import para funcionar o framework
import org.springframework.stereotype.Service

// import para puxar a data na hora e adicionar
import java.time.LocalDateTime

// imposts dos meus arquivos criados
import com.biblioteca.api.dto.CreateBookRequest
import com.biblioteca.api.dto.CreateBookResponse

import com.biblioteca.api.repository.BookRepository

import com.biblioteca.api.model.Book
import com.biblioteca.api.model.BookStatus

@Service
class BookService(private val repository: BookRepository) {

    fun create(request: CreateBookRequest): CreateBookResponse {
        val book = Book(
            title = request.title,
            author = request.author,
            isbn = request.isbn,
            category = request.category,
            status = request.status ?: BookStatus.AVAILABLE,
            registeredAt = LocalDateTime.now(),
            updatedAt = null
        )

        val savedBook = repository.save(book)

        return CreateBookResponse(
            id = savedBook.id!!,
            title = savedBook.title,
            author = savedBook.author,
            isbn = savedBook.isbn,
            category = savedBook.category,
            status = savedBook.status,
            registeredAt = savedBook.registeredAt
        )
    }

    fun findAll(): List<Book> {
        return repository.findAll()
    }
}