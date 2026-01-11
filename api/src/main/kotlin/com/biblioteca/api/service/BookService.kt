package com.biblioteca.api.service

//import para funcionar o framework
import org.springframework.stereotype.Service

// import para puxar a data na hora e adicionar
import java.time.LocalDateTime

// imposts dos meus arquivos criados
import com.biblioteca.api.dto.CreateBookRequest

import com.biblioteca.api.repository.BookRepository

import com.biblioteca.api.model.Book
import com.biblioteca.api.model.BookStatus
import com.biblioteca.api.model.Category

@Service
class BookService(private val repository: BookRepository) {
    fun create(request: CreateBookRequest): Book {
        val book = Book(
            title = request.title,
            author = request.author,
            isbn = request.isbn,
            category = request.category ?: Category.OTHER,
            status = request.status ?: BookStatus.AVAILABLE,
            registeredAt = LocalDateTime.now(),
            updateAt = null
        )

        return repository.save(book)
    }
    fun findAll(): List<Book>{
        return repository.findAll()
    }
}