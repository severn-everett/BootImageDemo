package com.severett.bootimagedemo.repo

import com.severett.bootimagedemo.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepo : JpaRepository<Author, Int>
