package dev.trodrigues.ead.authuser.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UsernameConstraintImpl : ConstraintValidator<UsernameConstraint, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrBlank() || value.contains(" ")) {
            return false
        }
        return true
    }
}
