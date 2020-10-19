package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web.security

import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl implements UserDetails{

    String username
    String password
    String displayName

    UserDetailsImpl(AppUser appUser) {
        this.username = appUser.username
        this.password = appUser.password
        this.displayName = appUser.displayName
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES
    }

    @Override
    String getPassword() {
        return password
    }

    @Override
    String getUsername() {
        return username
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }
}
