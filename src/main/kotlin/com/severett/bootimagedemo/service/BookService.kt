package com.severett.bootimagedemo.service

import com.severett.bootimagedemo.dto.BookDTO
import com.severett.bootimagedemo.model.Book
import com.severett.bootimagedemo.repo.AuthorRepo
import com.severett.bootimagedemo.repo.BookRepo
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
open class BookService(private val authorRepo: AuthorRepo, private val bookRepo: BookRepo) {
    fun getBook(id: Int): BookDTO {
        return bookRepo.findByIdOrNull(id)!!.let { BookDTO(title = it.title, authorId = it.author.id) }
    }

    @Transactional
    fun saveBook(bookDTO: BookDTO) {
        bookRepo.save(
            Book(
                title = bookDTO.title,
                author = authorRepo.getReferenceById(bookDTO.authorId)
            )
        )
    }

    @Transactional
    fun deleteBook(id: Int) {
        bookRepo.deleteById(id)
    }
}
