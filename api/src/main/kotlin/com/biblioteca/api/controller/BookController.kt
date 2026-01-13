package com.biblioteca.api.controller

import com.biblioteca.api.dto.CreateBookRequest
import com.biblioteca.api.dto.CreateBookResponse
import com.biblioteca.api.dto.UpdateBookRequest

import com.biblioteca.api.model.Book
import com.biblioteca.api.model.BookStatus
import com.biblioteca.api.model.Category

import com.biblioteca.api.service.BookService

import com.biblioteca.api.repository.BookRepository

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam

import jakarta.validation.Valid

@RestController
@RequestMapping("api/books")
class BookController(
    private val bookService: BookService
) {
    @GetMapping
    fun findAll(): List<Book> {
        return bookService.findAll()
    }

    @PostMapping
    fun create(@Valid @RequestBody request: CreateBookRequest): ResponseEntity<CreateBookResponse> {
        val book = bookService.create(request)

        return ResponseEntity.status(HttpStatus.CREATED).body(book)
    }

    @PatchMapping("/{id}/status")
    fun updateBook(
        @PathVariable id: Long,
        @RequestBody request: UpdateBookRequest
    ): ResponseEntity<CreateBookResponse> {
        val bookUpdated = bookService.updateStatus(id, request)
        return if (bookUpdated != null) {
            ResponseEntity.ok(bookUpdated)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}