package com.severett.bootimagedemo.controller

import com.severett.bootimagedemo.dto.BookDTO
import com.severett.bootimagedemo.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {
    @GetMapping("/{id}")
    @ResponseBody
    fun getBook(@PathVariable id: Int) = bookService.getBook(id)

    @PostMapping("")
    fun saveBook(@RequestBody bookDTO: BookDTO) {
        bookService.saveBook(bookDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Int) {
        bookService.deleteBook(id)
    }
}
