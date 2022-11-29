package com.severett.bootimagedemo.controller

import com.severett.bootimagedemo.dto.AuthorDTO
import com.severett.bootimagedemo.service.AuthorService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")
class AuthorController(private val authorService: AuthorService) {
    @GetMapping("/{id}")
    @ResponseBody
    fun getAuthor(@PathVariable id: Int) = authorService.getAuthor(id)

    @PostMapping("")
    fun saveAuthor(@RequestBody authorDTO: AuthorDTO) {
        authorService.saveAuthor(authorDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteAuthor(@PathVariable id: Int) {
        authorService.deleteAuthor(id)
    }
}
