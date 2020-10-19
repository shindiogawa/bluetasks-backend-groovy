package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web.security

import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUser
import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl implements UserDetailsService{

    AppUserRepository appUserRepository

    @Autowired
    UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username)
        if(appUser == null) {
            throw new UsernameNotFoundException(username)
        }

        return new UserDetailsImpl(appUser)
    }
}
