package dev.trodrigues.ead.authuser.specifications

import dev.trodrigues.ead.authuser.controllers.filters.UserFilter
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Predicate

object UserSpecification {

    fun users(filter: UserFilter): Specification<UserModel> {
        return Specification { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            filter.userType?.let {
                predicates.add(
                    criteriaBuilder.equal(criteriaBuilder.upper(root.get("userType")), it)
                )
            }

            filter.userStatus?.let {
                predicates.add(
                    criteriaBuilder.equal(criteriaBuilder.upper(root.get("userType")), it)
                )
            }

            filter.email?.let {
                predicates.add(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%${it.lowercase()}%")
                )
            }

            filter.fullName?.let {
                predicates.add(
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("fullName")), "%${it.uppercase()}%")
                )
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

}
