package dev.trodrigues.ead.authuser.specifications

import dev.trodrigues.ead.authuser.controllers.requests.UserFilter
import dev.trodrigues.ead.authuser.models.UserModel
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Predicate

object UserSpecification {

    fun users(filter: UserFilter): Specification<UserModel> {
        return Specification { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            filter.userType?.let {
                predicates.add(
                    criteriaBuilder.equal(root.get<String>("userType"), filter.userType)
                )
            }

            filter.userStatus?.let {
                predicates.add(
                    criteriaBuilder.equal(root.get<String>("userStatus"), filter.userStatus)
                )
            }

            filter.email?.let {
                predicates.add(
                    criteriaBuilder.like(root.get("email"), "%${filter.email}%")
                )
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

}
