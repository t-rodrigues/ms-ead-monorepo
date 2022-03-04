package dev.trodrigues.ead.course.validation

import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UserInstructorValidator() : ConstraintValidator<UserInstructor, UUID> {

    override fun isValid(value: UUID?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }
        return validateUserInstructor(value)
    }

    private fun validateUserInstructor(userInstructor: UUID): Boolean {
        return true
//        return try {
//            val userResponse = authUserClient.getUserById(userInstructor)
//            userResponse.userType != UserType.STUDENT
//        } catch (ex: FeignException) {
//            !ex.status().equals(HttpStatus.NOT_FOUND.value())
//        }
    }

}
