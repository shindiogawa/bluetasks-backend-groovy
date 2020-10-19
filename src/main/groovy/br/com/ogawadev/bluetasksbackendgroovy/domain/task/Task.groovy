package br.com.ogawadev.bluetasksbackendgroovy.domain.task

import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUser
import groovy.transform.Canonical
import lombok.NoArgsConstructor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import java.time.LocalDate

@Entity
class Task {

    Task() {}

    Task(String description, LocalDate whenToDo, boolean done) {
        this.description = description
        this.whenToDo = whenToDo
        this.done = done
    }

    @Id
    @GeneratedValue
    Integer id

    String description

    LocalDate whenToDo

    boolean done = false

    @ManyToOne
    @JoinColumn(name="app_user_id")
    AppUser appUser


}
