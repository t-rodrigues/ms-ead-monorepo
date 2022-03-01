package dev.trodrigues.ead.course.validation

import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.course.clients.AuthUserClient
import feign.FeignException
import org.springframework.http.HttpStatus
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UserInstructorValidator(
    private val authUserClient: AuthUserClient
) : ConstraintValidator<UserInstructor, UUID> {

    override fun isValid(value: UUID?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }
        return validateUserInstructor(value)
    }

    private fun validateUserInstructor(userInstructor: UUID): Boolean {
        return try {
            val userResponse = authUserClient.getUserById(userInstructor)
            userResponse.userType != UserType.STUDENT
        } catch (ex: FeignException) {
            !ex.status().equals(HttpStatus.NOT_FOUND.value())
        }
    }

}
