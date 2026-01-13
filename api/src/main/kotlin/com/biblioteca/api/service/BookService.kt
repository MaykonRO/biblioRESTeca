package com.biblioteca.api.service

//import para funcionar o framework
import org.springframework.stereotype.Service

// import para puxar a data na hora e adicionar
import java.time.LocalDateTime

// imposts dos meus arquivos criados
import com.biblioteca.api.dto.CreateBookRequest
import com.biblioteca.api.dto.CreateBookResponse
import com.biblioteca.api.dto.UpdateBookRequest

import com.biblioteca.api.repository.BookRepository

import com.biblioteca.api.model.Book
import com.biblioteca.api.model.BookStatus
import com.biblioteca.api.model.Category


@Service
class BookService(private val repository: BookRepository) {

    fun create(request: CreateBookRequest): CreateBookResponse {

        val validarCategory = when (request.category.uppercase()) {
            "TECHNOLOGY" -> Category.TECHNOLOGY
            "FICTION" -> Category.FICTION
            "SCIENCE" -> Category.SCIENCE
            "BUSINESS" -> Category.BUSINESS
            "OTHER" -> Category.OTHER
            else -> Category.OTHER
        }

        val book = Book(
            title = request.title,
            author = request.author,
            isbn = request.isbn,
            category = validarCategory,
            status = BookStatus.AVAILABLE,
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
            registeredAt = savedBook.registeredAt,
            updateAt = savedBook.updatedAt
        )
    }

    fun findAll(): List<Book> {
        return repository.findAll()
    }

    fun updateStatus(id: Long, request: UpdateBookRequest): CreateBookResponse? {
        val bookOptional = repository.findById(id)
        if (bookOptional.isEmpty) {
            return null

        }

        val bookUpdate = bookOptional.get()

        val validarStatus = request.status

        bookUpdate.status = when (validarStatus.uppercase()) {
            "AVAILABLE" -> BookStatus.AVAILABLE
            "BORROWED" -> BookStatus.BORROWED
            "READING" -> BookStatus.READING
            else -> BookStatus.AVAILABLE
        }
        bookUpdate.updatedAt = LocalDateTime.now()

        val savedBook = repository.save(bookUpdate)

        return CreateBookResponse(
            id = savedBook.id!!,
            title = savedBook.title,
            author = savedBook.author,
            isbn = savedBook.isbn,
            category = savedBook.category,
            status = savedBook.status,
            registeredAt = savedBook.registeredAt,
            updateAt = savedBook.updatedAt
        )
    }
}