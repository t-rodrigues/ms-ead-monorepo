package dev.trodrigues.ead.notification.security.authorization

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ADMIN') || #userId == authentication.principal.id")
annotation class UserCanOnlyAccessTheirOwnResource
