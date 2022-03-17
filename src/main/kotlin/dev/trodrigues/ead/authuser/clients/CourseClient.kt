package dev.trodrigues.ead.authuser.clients

import dev.trodrigues.ead.authuser.controllers.responses.CourseResponse
import dev.trodrigues.ead.authuser.controllers.responses.PageResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "ead-course-service")
interface CourseClient {

    @GetMapping("/courses")
    fun getCoursesByUser(
        @RequestHeader("Authorization") accessToken: String,
        @RequestParam userId: UUID,
        @PageableDefault(size = 10, sort = ["creationDate"], direction = Sort.Direction.DESC) pageable: Pageable
    ): PageResponse<CourseResponse>

}
