package com.actionplatform.webkotlinspring.core

import org.springframework.http.HttpStatus

/** A rule violation the caller can act on. Services throw it; the API answers it in one shape. */
open class DomainException(
    val status: HttpStatus,
    val code: String,
    override val message: String,
    val field: String? = null,
) : RuntimeException(message)

class NotFoundException(
    message: String,
) : DomainException(HttpStatus.NOT_FOUND, "not_found", message)

class ValidationException(
    message: String,
    field: String,
) : DomainException(HttpStatus.UNPROCESSABLE_ENTITY, "invalid", message, field)

class ConflictException(
    message: String,
) : DomainException(HttpStatus.CONFLICT, "conflict", message)
