package dev.trodrigues.ead.authuser.specifications

import dev.trodrigues.ead.authuser.models.UserModel
import net.kaczmarzyk.spring.data.jpa.domain.Equal
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.data.jpa.domain.Specification

object SpecificationTemplate {

    @And(
        Spec(path = "userType", spec = Equal::class),
        Spec(path = "userStatus", spec = Equal::class),
        Spec(path = "email", spec = LikeIgnoreCase::class)
    )
    interface UserSpec : Specification<UserModel>

}
