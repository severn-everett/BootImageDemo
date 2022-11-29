package com.severett.bootimagedemo.service

import com.severett.bootimagedemo.dto.AuthorDTO
import com.severett.bootimagedemo.model.Author
import com.severett.bootimagedemo.repo.AuthorRepo
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepo: AuthorRepo) {

    suspend fun getAuthor(id: Int): AuthorDTO {
        return withContext(Dispatchers.IO) {
            authorRepo.findByIdOrNull(id)?.let { AuthorDTO(firstName = it.firstName, lastName = it.lastName) }
                ?: throw EntityNotFoundException("No author found with id #$id")
        }
    }

    @Transactional
    suspend fun saveAuthor(authorDTO: AuthorDTO) {
        withContext(Dispatchers.IO) {
            authorRepo.save(Author(firstName = authorDTO.firstName, lastName = authorDTO.lastName))
        }
    }

    @Transactional
    suspend fun deleteAuthor(id: Int) {
        withContext(Dispatchers.IO) {
            authorRepo.deleteById(id)
        }
    }
}
