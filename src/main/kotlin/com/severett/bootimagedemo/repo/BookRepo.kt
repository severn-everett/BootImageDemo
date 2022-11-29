package com.severett.bootimagedemo.repo

import com.severett.bootimagedemo.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepo : JpaRepository<Book, Int> {
}
