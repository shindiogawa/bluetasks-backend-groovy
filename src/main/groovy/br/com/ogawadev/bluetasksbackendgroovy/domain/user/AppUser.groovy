package br.com.ogawadev.bluetasksbackendgroovy.domain.user

import groovy.transform.Canonical
import lombok.AllArgsConstructor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="app_user")
@Canonical
class AppUser {

    AppUser(){}
    AppUser(String username, String password, String displayName) {
        this.username = username
        this.password = password
        this.displayName = displayName
    }

    @Id
    @GeneratedValue
    Integer id

    String username

    String password

    String displayName

}
