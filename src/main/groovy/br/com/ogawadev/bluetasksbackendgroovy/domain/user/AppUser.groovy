package br.com.ogawadev.bluetasksbackendgroovy.domain.user

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="app_user")
@Canonical
class AppUser {

    @Id
    @GeneratedValue
    Integer id

    String username

    String password

    String displayName
}
