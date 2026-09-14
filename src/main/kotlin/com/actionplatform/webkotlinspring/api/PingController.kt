package com.actionplatform.webkotlinspring.api

import com.actionplatform.webkotlinspring.Version
import com.actionplatform.webkotlinspring.dto.Health
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/** Ping. Every API exposes it at the root. */
@RestController
class PingController {
    @GetMapping("/ping")
    fun ping() = Health("ok", Version.VERSION)
}
