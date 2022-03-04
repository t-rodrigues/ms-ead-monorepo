package dev.trodrigues.ead.course.specifications

import dev.trodrigues.ead.course.controllers.filters.CourseFilter
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.UserModel
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.Predicate

object CourseSpec {

    fun courses(filter: CourseFilter): Specification<CourseModel> {
        return Specification { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            filter.userId?.let { userId ->
                val courseProd = root.join<CourseModel, UserModel>("users")
                predicates.add(
                    criteriaBuilder.equal(courseProd.get<UUID>("userId"), userId)
                )
            }

            filter.name?.let {
                predicates.add(
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%${it.uppercase()}%")
                )
            }

            filter.courseLevel?.let {
                predicates.add(
                    criteriaBuilder.equal(criteriaBuilder.upper(root.get("courseLevel")), it)
                )
            }

            filter.courseStatus?.let {
                predicates.add(
                    criteriaBuilder.equal(criteriaBuilder.upper(root.get("courseStatus")), it)
                )
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

}
