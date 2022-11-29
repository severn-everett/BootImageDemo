package com.severett.bootimagedemo.repo

import com.severett.bootimagedemo.model.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepo : JpaRepository<Author, Int> {
}
