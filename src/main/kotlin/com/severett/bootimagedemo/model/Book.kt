package com.severett.bootimagedemo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator

@Entity
class Book(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var title: String,
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    var author: Author
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
