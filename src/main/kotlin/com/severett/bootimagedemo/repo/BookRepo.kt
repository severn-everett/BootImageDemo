package com.severett.bootimagedemo.repo

import com.severett.bootimagedemo.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepo : JpaRepository<Book, Int>
