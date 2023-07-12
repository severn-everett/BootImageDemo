package com.severett.bootimagedemo.service

import com.severett.bootimagedemo.dto.BookDTO
import com.severett.bootimagedemo.model.Book
import com.severett.bootimagedemo.repo.AuthorRepo
import com.severett.bootimagedemo.repo.BookRepo
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(private val authorRepo: AuthorRepo, private val bookRepo: BookRepo) {
    suspend fun getBook(id: Int): BookDTO {
        return withContext(Dispatchers.IO) {
            bookRepo.findByIdOrNull(id)?.let { BookDTO(title = it.title, price = it.price, authorId = it.author.id) }
                ?: throw EntityNotFoundException("No book found with id #$id")
        }
    }

    @Transactional
    suspend fun saveBook(bookDTO: BookDTO) {
        withContext(Dispatchers.IO) {
            bookRepo.save(
                Book(
                    title = bookDTO.title,
                    price = bookDTO.price,
                    author = authorRepo.getReferenceById(bookDTO.authorId)
                )
            )
        }
    }

    @Transactional
    suspend fun deleteBook(id: Int) {
        withContext(Dispatchers.IO) {
            bookRepo.deleteById(id)
        }
    }
}
