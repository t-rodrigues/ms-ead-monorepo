package dev.trodrigues.ead.course.enums

import org.springframework.http.HttpStatus

enum class Errors(val status: HttpStatus, val message: String) {

    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found")

}
