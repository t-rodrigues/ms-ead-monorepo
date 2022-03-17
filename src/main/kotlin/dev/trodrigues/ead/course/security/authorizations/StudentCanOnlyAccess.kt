package dev.trodrigues.ead.course.security.authorizations

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('STUDENT')")
annotation class StudentCanOnlyAccess
