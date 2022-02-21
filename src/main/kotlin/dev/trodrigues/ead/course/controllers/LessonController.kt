package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.requests.LessonPostRequest
import dev.trodrigues.ead.course.controllers.requests.LessonPutRequest
import dev.trodrigues.ead.course.controllers.responses.LessonResponse
import dev.trodrigues.ead.course.extension.toModel
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.services.LessonService
import dev.trodrigues.ead.course.services.ModuleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/modules/{moduleId}/lessons")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class LessonController(
    private val lessonService: LessonService,
    private val moduleService: ModuleService
) {

    @PostMapping
    fun saveLesson(
        @PathVariable moduleId: UUID,
        @Valid @RequestBody lessonPostRequest: LessonPostRequest
    ): ResponseEntity<LessonResponse> {
        val module = moduleService.getModuleById(moduleId)
        val lesson = lessonService.createLesson(lessonPostRequest.toModel(module))
        val uri = URI("/modules/$moduleId/lessons/${lesson.id}")
        return ResponseEntity.created(uri).body(lesson.toResponse())
    }

    @PutMapping("/{lessonId}")
    fun updateLesson(
        @PathVariable moduleId: UUID,
        @PathVariable lessonId: UUID,
        @Valid @RequestBody lessonPutRequest: LessonPutRequest
    ): LessonResponse {
        val oldLesson = lessonService.getLessonIntoModule(moduleId, lessonId)
        val updatedLesson = lessonService.update(lessonPutRequest.toModel(oldLesson))
        return updatedLesson.toResponse()
    }

    @DeleteMapping("/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteLesson(@PathVariable moduleId: UUID, @PathVariable lessonId: UUID) {
        lessonService.deleteLesson(moduleId, lessonId)
    }

}
