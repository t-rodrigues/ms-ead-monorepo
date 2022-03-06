package dev.trodrigues.ead.course.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UserInstructorValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class UserInstructor(
    val message: String = "Invalid userInstructor",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
