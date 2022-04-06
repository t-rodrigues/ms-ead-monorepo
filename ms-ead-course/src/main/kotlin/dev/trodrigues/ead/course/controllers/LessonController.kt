package dev.trodrigues.ead.course.controllers

import dev.trodrigues.ead.course.controllers.filters.LessonFilter
import dev.trodrigues.ead.course.controllers.requests.LessonPostRequest
import dev.trodrigues.ead.course.controllers.requests.LessonPutRequest
import dev.trodrigues.ead.course.controllers.responses.LessonResponse
import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.extension.toModel
import dev.trodrigues.ead.course.extension.toPageResponse
import dev.trodrigues.ead.course.extension.toResponse
import dev.trodrigues.ead.course.security.authorizations.InstructorCanOnlyAccess
import dev.trodrigues.ead.course.security.authorizations.StudentCanOnlyAccess
import dev.trodrigues.ead.course.services.LessonService
import dev.trodrigues.ead.course.services.ModuleService
import dev.trodrigues.ead.course.specifications.LessonSpec
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*
import javax.validation.Valid

@InstructorCanOnlyAccess
@RestController
@RequestMapping("/modules/{moduleId}/lessons")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class LessonController(
    private val lessonService: LessonService,
    private val moduleService: ModuleService
) {

    @StudentCanOnlyAccess
    @GetMapping
    fun getLessonsByModule(
        @PathVariable moduleId: UUID,
        filter: LessonFilter,
        @PageableDefault(size = 15, sort = ["creationDate"]) pageable: Pageable
    ): PageResponse<LessonResponse> {
        val lessons = lessonService.getLessonsIntoModule(LessonSpec.lessons(moduleId, filter), pageable)
        return lessons.map { it.toResponse() }.toPageResponse()
    }

    @StudentCanOnlyAccess
    @GetMapping("/{lessonId}")
    fun getLesson(
        @PathVariable moduleId: UUID,
        @PathVariable lessonId: UUID
    ): LessonResponse {
        val lesson = lessonService.getLessonIntoModule(moduleId, lessonId)
        return lesson.toResponse()
    }

    @InstructorCanOnlyAccess
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

    @InstructorCanOnlyAccess
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

    @InstructorCanOnlyAccess
    @DeleteMapping("/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteLesson(@PathVariable moduleId: UUID, @PathVariable lessonId: UUID) {
        lessonService.deleteLesson(moduleId, lessonId)
    }

}
