package dev.trodrigues.ead.authuser.controllers

import dev.trodrigues.ead.authuser.controllers.requests.PatchInstructorRequest
import dev.trodrigues.ead.authuser.controllers.responses.UserResponse
import dev.trodrigues.ead.authuser.enums.UserType
import dev.trodrigues.ead.authuser.extension.toResponse
import dev.trodrigues.ead.authuser.security.authorization.AdminCanOnlyAccess
import dev.trodrigues.ead.authuser.services.UserService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/instructors")
@CrossOrigin(originPatterns = ["*"], maxAge = 3600)
class InstructorController(
    private val userService: UserService
) {

    @AdminCanOnlyAccess
    @PatchMapping("/subscription")
    fun saveSubscriptionInstructor(@RequestBody @Valid patchInstructorRequest: PatchInstructorRequest): UserResponse {
        val user = userService.updateUserType(patchInstructorRequest.userId!!, UserType.INSTRUCTOR)
        return user.toResponse()
    }

}
