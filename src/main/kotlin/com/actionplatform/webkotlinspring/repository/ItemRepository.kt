package com.actionplatform.webkotlinspring.repository

import com.actionplatform.webkotlinspring.dto.Item
import org.springframework.stereotype.Repository

/** In-memory store standing in for a database or an external API. Replace it; keep the methods. */
@Repository
class ItemRepository {
    private val rows = linkedMapOf<Int, Item>()
    private var lastId = 0

    @Synchronized
    fun list(): List<Item> = rows.values.toList()

    @Synchronized
    fun get(id: Int): Item? = rows[id]

    @Synchronized
    fun add(name: String): Item {
        lastId += 1
        val item = Item(lastId, name)
        rows[item.id] = item
        return item
    }
}
