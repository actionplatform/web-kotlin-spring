package com.actionplatform.webkotlinspring.core

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/** Turns a DomainException into {"detail": {"code", "message", "field"}}. */
@RestControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(DomainException::class)
    fun domain(exception: DomainException): ResponseEntity<Map<String, Any>> {
        val detail = linkedMapOf<String, Any>("code" to exception.code, "message" to exception.message)
        exception.field?.let { detail["field"] = it }
        return ResponseEntity.status(exception.status).body(mapOf("detail" to detail))
    }
}
