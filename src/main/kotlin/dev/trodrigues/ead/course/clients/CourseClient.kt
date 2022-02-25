package dev.trodrigues.ead.course.clients

import dev.trodrigues.ead.course.controllers.responses.PageResponse
import dev.trodrigues.ead.course.controllers.responses.UserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "users", url = "\${users.url}")
interface CourseClient {

    @GetMapping("/users")
    fun getUsersByCourse(@RequestParam courseId: UUID, pageable: Pageable): PageResponse<UserResponse>

}
