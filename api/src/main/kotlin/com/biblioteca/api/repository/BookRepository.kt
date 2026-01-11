package com.biblioteca.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.biblioteca.api.model.Book

interface BookRepository  : JpaRepository<Book, Long>