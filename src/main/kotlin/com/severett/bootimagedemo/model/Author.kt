package com.severett.bootimagedemo.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Author(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var firstName: String,
    var lastName: String,
    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL])
    var books: List<Book> = emptyList()
) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Author) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
