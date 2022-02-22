package dev.trodrigues.ead.course.specifications

import dev.trodrigues.ead.course.controllers.filters.LessonFilter
import dev.trodrigues.ead.course.models.LessonModel
import dev.trodrigues.ead.course.models.ModuleModel
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.Predicate

object LessonSpec {

    fun lessons(moduleId: UUID, filter: LessonFilter): Specification<LessonModel> {
        return Specification { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()
            val module = query.from(ModuleModel::class.java)

            predicates.add(
                criteriaBuilder.equal(module.get<String>("id"), moduleId)
            )

            predicates.add(
                criteriaBuilder.isMember(root, module.get("lessons"))
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
