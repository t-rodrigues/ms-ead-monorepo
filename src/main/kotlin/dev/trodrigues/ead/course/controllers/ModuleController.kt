package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.ModulePostRequest
import dev.trodrigues.ead.course.controllers.responses.ModuleResponse
import dev.trodrigues.ead.course.extension.toModel
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.services.CourseService
import dev.trodrigues.ead.course.services.ModuleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/courses/{courseId}/modules")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class ModuleController(
    private val moduleService: ModuleService,
    private val courseService: CourseService
) {

    @PostMapping
    fun saveModule(
        @PathVariable courseId: UUID,
        @Valid @RequestBody modulePostRequest: ModulePostRequest
    ): ResponseEntity<ModuleResponse> {
        val course = courseService.getCourseById(courseId)
        val module = moduleService.create(modulePostRequest.toModel(course)).toResponse()
        val uri = URI("/courses/$courseId/modules/${module.id}")
        return ResponseEntity.created(uri).body(module)
    }

}
