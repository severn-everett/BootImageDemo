package com.severett.bootimagedemo.dto

import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(val title: String, val authorId: Int)
