package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.CourseRequest
import dev.trodrigues.ead.course.controllers.responses.CourseResponse
import dev.trodrigues.ead.course.extension.toCourseModel
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.services.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class CourseController(
    private val courseService: CourseService
) {

    @PostMapping
    fun saveCourse(@Valid @RequestBody courseRequest: CourseRequest): ResponseEntity<CourseResponse> {
        val course = courseService.create(courseRequest.toCourseModel())
        val uri = URI("/courses/${course.id!!}")
        return ResponseEntity.created(uri).body(course.toResponse())
    }

}
