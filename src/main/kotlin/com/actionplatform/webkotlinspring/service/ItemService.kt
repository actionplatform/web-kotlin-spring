package com.actionplatform.webkotlinspring.service

import com.actionplatform.webkotlinspring.core.NotFoundException
import com.actionplatform.webkotlinspring.core.ValidationException
import com.actionplatform.webkotlinspring.dto.Item
import com.actionplatform.webkotlinspring.repository.ItemRepository
import org.springframework.stereotype.Service

const val MAX_NAME = 40

/** Business rules for items. Receives the repository; never touches HTTP. */
@Service
class ItemService(
    private val repository: ItemRepository,
) {
    fun list(): List<Item> = repository.list()

    fun get(id: Int): Item = repository.get(id) ?: throw NotFoundException("item $id not found")

    fun create(rawName: String?): Item {
        val name = rawName?.trim().orEmpty()
        if (name.isEmpty()) {
            throw ValidationException("name is required", "name")
        }
        if (name.length > MAX_NAME) {
            throw ValidationException("name is longer than $MAX_NAME characters", "name")
        }
        return repository.add(name)
    }
}
