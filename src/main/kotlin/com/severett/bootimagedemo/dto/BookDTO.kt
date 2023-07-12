package com.severett.bootimagedemo.dto

import com.severett.bootimagedemo.serde.BigDecimalSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class BookDTO(
    val title: String,
    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,
    val authorId: Int
)
