package com.biblioteca.api.controller

import com.biblioteca.api.dto.CreateBookRequest
import com.biblioteca.api.dto.CreateBookResponse
import com.biblioteca.api.model.Book
import com.biblioteca.api.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun create(@RequestBody request: CreateBookRequest): CreateBookResponse {
        return bookService.create(request)
    }
}