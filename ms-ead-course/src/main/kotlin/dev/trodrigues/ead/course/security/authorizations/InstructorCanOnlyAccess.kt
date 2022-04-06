package dev.trodrigues.ead.course.security.authorizations

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('INSTRUCTOR')")
annotation class InstructorCanOnlyAccess
