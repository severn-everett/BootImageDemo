package com.severett.bootimagedemo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class Book(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var title: String,
    var price: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    var author: Author
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
