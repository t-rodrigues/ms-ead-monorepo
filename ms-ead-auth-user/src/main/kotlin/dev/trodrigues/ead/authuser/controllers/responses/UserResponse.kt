package dev.trodrigues.ead.authuser.controllers.responses

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import dev.trodrigues.ead.authuser.enums.UserStatus
import dev.trodrigues.ead.authuser.enums.UserType
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserResponse(
    val id: UUID? = null,
    val username: String,
    val email: String,
    val fullName: String,
    val userStatus: UserStatus,
    val userType: UserType,
    val cpf: String,
    val phoneNumber: String? = null,
    val imageUrl: String? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val creationDate: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    val lastUpdatedDate: LocalDateTime? = null
)
