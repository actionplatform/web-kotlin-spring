package com.actionplatform.webkotlinspring.service

import com.actionplatform.webkotlinspring.core.NotFoundException
import com.actionplatform.webkotlinspring.core.ValidationException
import com.actionplatform.webkotlinspring.repository.ItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class ItemServiceTest {
    private val service = ItemService(ItemRepository())

    @Test
    fun `creates and gets without http`() {
        val item = service.create(" pen ")
        assertEquals("pen", item.name)
        assertEquals(item, service.get(item.id))
        assertEquals(1, service.list().size)
    }

    @Test
    fun `refuses bad names and unknown ids`() {
        assertThrows(ValidationException::class.java) { service.create("") }
        assertThrows(ValidationException::class.java) { service.create("x".repeat(41)) }
        assertThrows(NotFoundException::class.java) { service.get(1) }
    }
}
