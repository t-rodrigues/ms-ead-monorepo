package dev.trodrigues.ead.authuser.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [UsernameConstraintImpl::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class UsernameConstraint(
    val message: String = "Username already taken",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
