package br.com.ogawadev.bluetasksbackendgroovy.domain.user

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
@RepositoryRestResource(exported = false)
interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    AppUser findByUsername(String username)

}