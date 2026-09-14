package com.actionplatform.webkotlinspring.api.v1

import com.actionplatform.webkotlinspring.dto.Item
import com.actionplatform.webkotlinspring.dto.ItemCreate
import com.actionplatform.webkotlinspring.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/** Example resource showing the layers: controller → service → repository. */
@RestController
@RequestMapping("/api/v1/items")
class ItemsController(
    private val service: ItemService,
) {
    @GetMapping
    fun list(): List<Item> = service.list()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody body: ItemCreate,
    ): Item = service.create(body.name)

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Int,
    ): Item = service.get(id)
}
