package dev.trodrigues.ead.course.validation

import dev.trodrigues.ead.course.enums.UserType
import dev.trodrigues.ead.course.services.UserService
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UserInstructorValidator(
    private val userService: UserService
) : ConstraintValidator<UserInstructor, UUID> {

    override fun isValid(value: UUID?, context: ConstraintValidatorContext?): Boolean {
        value?.let {
            return validateUserInstructor(it)
        }
        return false
    }

    private fun validateUserInstructor(userInstructor: UUID): Boolean {
        return try {
            val user = userService.getUserById(userInstructor)
            user.userType != UserType.STUDENT
        } catch (ex: Exception) {
            false
        }
    }

}
