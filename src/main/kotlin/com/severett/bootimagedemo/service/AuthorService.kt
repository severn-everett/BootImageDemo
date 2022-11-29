package com.severett.bootimagedemo.service

import com.severett.bootimagedemo.dto.AuthorDTO
import com.severett.bootimagedemo.model.Author
import com.severett.bootimagedemo.repo.AuthorRepo
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
open class AuthorService(private val authorRepo: AuthorRepo) {

    fun getAuthor(id: Int): AuthorDTO {
        return authorRepo.findByIdOrNull(id)!!.let { AuthorDTO(firstName = it.firstName, lastName = it.lastName) }
    }

    @Transactional
    fun saveAuthor(authorDTO: AuthorDTO) {
        authorRepo.save(Author(firstName = authorDTO.firstName, lastName = authorDTO.lastName))
    }

    @Transactional
    fun deleteAuthor(id: Int) {
        authorRepo.deleteById(id)
    }
}
