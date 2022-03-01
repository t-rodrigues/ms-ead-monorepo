package dev.trodrigues.ead.authuser.services.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ObjectNotFoundException(override val message: String) : RuntimeException()
