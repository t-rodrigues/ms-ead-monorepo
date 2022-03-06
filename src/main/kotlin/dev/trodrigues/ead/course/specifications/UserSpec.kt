package dev.trodrigues.ead.course.specifications

import dev.trodrigues.ead.course.controllers.filters.UserFilter
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.Predicate

object UserSpec {

    fun getUsers(filter: UserFilter, courseId: UUID? = null): Specification<UserModel> {
        return Specification { root, query, cb ->
            val predicates = mutableListOf<Predicate>()

            courseId?.let { courseId ->
                val course = query.from(CourseModel::class.java)
                predicates.add(
                    cb.and(
                        cb.equal(course.get<UUID>("id"), courseId),
                        cb.isMember(root, course.get("users"))
                    )
                )
            }

            filter.email?.let { email ->
                predicates.add(
                    cb.like(cb.lower(root.get("email")), "%${email.lowercase()}%")
                )
            }

            filter.fullName?.let { fullName ->
                predicates.add(
                    cb.like(cb.upper(root.get("fullName")), "%${fullName.uppercase()}%")
                )
            }

            filter.userStatus?.let { status ->
                predicates.add(
                    cb.equal(root.get<String>("userStatus"), status)
                )
            }

            filter.userType?.let { type ->
                predicates.add(
                    cb.equal(root.get<String>("userType"), type)
                )
            }

            cb.and(*predicates.toTypedArray())
        }
    }

}
