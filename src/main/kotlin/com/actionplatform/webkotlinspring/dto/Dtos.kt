package com.actionplatform.webkotlinspring.dto

data class Health(
    val status: String,
    val version: String,
)

data class Item(
    val id: Int,
    val name: String,
)

data class ItemCreate(
    val name: String?,
)
