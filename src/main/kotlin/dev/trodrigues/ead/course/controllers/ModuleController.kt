package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.ModulePostRequest
import dev.trodrigues.ead.course.controllers.requests.ModulePutRequest
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

    @GetMapping
    fun getModules(@PathVariable courseId: UUID): List<ModuleResponse> {
        val modules = moduleService.getModulesByCourse(courseId)
        return modules.map { it.toResponse() }
    }

    @PostMapping
    fun saveModule(
        @PathVariable courseId: UUID,
        @Valid @RequestBody modulePostRequest: ModulePostRequest
    ): ResponseEntity<ModuleResponse> {
        val course = courseService.getCourseById(courseId)
        val module = moduleService.create(modulePostRequest.toModel(course))
        val uri = URI("/courses/$courseId/modules/${module.id}")
        return ResponseEntity.created(uri).body(module.toResponse())
    }

    @PutMapping("/{moduleId}")
    fun updateModule(
        @PathVariable courseId: UUID,
        @PathVariable moduleId: UUID,
        @Valid @RequestBody modulePutRequest: ModulePutRequest
    ): ModuleResponse {
        val oldModule = moduleService.getModuleIntoCourse(courseId, moduleId)
        val updatedModule = moduleService.update(modulePutRequest.toModel(oldModule))

        return updatedModule.toResponse()
    }

    @DeleteMapping("/{moduleId}")
    fun deleteModule(@PathVariable courseId: UUID, @PathVariable moduleId: UUID) {
        moduleService.delete(courseId, moduleId)
    }

}
