package dev.trodrigues.ead.course.specifications

import dev.trodrigues.ead.course.controllers.filters.CourseFilter
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.Predicate

object CourseSpec {

    fun getCourses(filter: CourseFilter): Specification<CourseModel> {
        return Specification { root, _, cb ->
            val predicates = mutableListOf<Predicate>()

            filter.userId?.let { userId ->
                val courseProd = root.join<CourseModel, UserModel>("users")
                predicates.add(
                    cb.equal(courseProd.get<UUID>("id"), userId)
                )
            }

            filter.name?.let { name ->
                predicates.add(
                    cb.like(cb.upper(root.get("name")), "%${name.uppercase()}%")
                )
            }

            filter.courseLevel?.let { courseLevel ->
                predicates.add(
                    cb.equal(cb.upper(root.get("courseLevel")), courseLevel)
                )
            }

            filter.courseStatus?.let { courseStatus ->
                predicates.add(
                    cb.equal(cb.upper(root.get("courseStatus")), courseStatus)
                )
            }

            cb.and(*predicates.toTypedArray())
        }
    }

}
