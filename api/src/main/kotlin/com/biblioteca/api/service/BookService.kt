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


@Service
class BookService(private val repository: BookRepository) {

    fun create(request: CreateBookRequest): CreateBookResponse {
        val book = Book(
            title = request.title,
            author = request.author,
            isbn = request.isbn,
            category = request.category,
            status = BookStatus.AVAILABLE,
            registeredAt = LocalDateTime.now(),
            updatedAt = null
        )

        val savedBook = repository.save(book)

        if (savedBook.updatedAt == null){

        }

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

    fun updateStatus(id: Long, request: UpdateBookRequest): CreateBookResponse{
        val bookUpdate = repository.findById(id).orElseThrow{
            NoSuchElementException("livro com id $id não encontrado")
        }
        bookUpdate.status = request.status
        bookUpdate.updatedAt = LocalDateTime.now()

        val savedBook = repository.save(bookUpdate)

        return CreateBookResponse(
            id = savedBook.id!!,
            title = savedBook.title,
            author = savedBook.author,
            isbn = savedBook.isbn,
            category = savedBook.category,
            status = bookUpdate.status,
            registeredAt = savedBook.registeredAt,
            updateAt = bookUpdate.updatedAt
        )
    }
}