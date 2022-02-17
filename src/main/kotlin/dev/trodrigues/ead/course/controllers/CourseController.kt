package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.CoursePostRequest
import dev.trodrigues.ead.course.controllers.requests.CoursePutRequest
import dev.trodrigues.ead.course.controllers.responses.CourseResponse
import dev.trodrigues.ead.course.extension.toCourseModel
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.services.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping
    fun getCourses(): List<CourseResponse> {
        return courseService.getCourses().map { it.toResponse() }
    }

    @GetMapping("/{courseId}")
    fun getCourseById(@PathVariable courseId: UUID): CourseResponse {
        return courseService.getCourseById(courseId).toResponse()
    }

    @PostMapping
    fun saveCourse(@Valid @RequestBody coursePostRequest: CoursePostRequest): ResponseEntity<CourseResponse> {
        val course = courseService.create(coursePostRequest.toCourseModel())
        val uri = URI("/courses/${course.id!!}")
        return ResponseEntity.created(uri).body(course.toResponse())
    }

    @PutMapping("/{courseId}")
    fun updateCourse(
        @PathVariable courseId: UUID,
        @Valid @RequestBody coursePutRequest: CoursePutRequest
    ): CourseResponse {
        return courseService.update(courseId, coursePutRequest).toResponse()
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable courseId: UUID) {
        courseService.delete(courseId)
    }

}
