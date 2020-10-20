package br.com.ogawadev.bluetasksbackendgroovy.domain.task

import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUser
import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

import javax.persistence.EntityNotFoundException
import javax.persistence.PrePersist

@Component
class TaskListener {

    private static AppUserRepository appUserRepository

    @Autowired
    void init(AppUserRepository appUserRepository) {
        TaskListener.appUserRepository = appUserRepository
    }

    @PrePersist
    void onPrePersistHandler(Task task) {
        if(task.getAppUser() == null) {
            String username =  SecurityContextHolder.getContext().getAuthentication().getName()
            AppUser appUser = appUserRepository.findByUsername(username)
            if(appUser == null) {
                throw new EntityNotFoundException("O usuário " + username + " não foi encontrado")
            }
            task.appUser = appUser
        }
    }
}
