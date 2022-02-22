package dev.trodrigues.ead.course.specifications

import dev.trodrigues.ead.course.controllers.filters.ModuleFilter
import dev.trodrigues.ead.course.models.CourseModel
import dev.trodrigues.ead.course.models.ModuleModel
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.Predicate

object ModuleSpec {

    fun modules(courseId: UUID, filter: ModuleFilter): Specification<ModuleModel> {
        return Specification { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()
            val course = query.from(CourseModel::class.java)

            predicates.add(
                criteriaBuilder.equal(course.get<String>("id"), courseId)
            )

            predicates.add(
                criteriaBuilder.isMember(root, course.get("modules"))
            )

            filter.title?.let {
                predicates.add(
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%${it.uppercase()}%")
                )
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

}
