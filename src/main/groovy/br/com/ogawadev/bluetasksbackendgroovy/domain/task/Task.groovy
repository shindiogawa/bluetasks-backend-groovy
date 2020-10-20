package br.com.ogawadev.bluetasksbackendgroovy.domain.task

import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUser
import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.Canonical
import lombok.NoArgsConstructor

import org.hibernate.validator.constraints.Length

import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import java.time.LocalDate

@Entity
@EntityListeners(TaskListener.class)
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

    @NotEmpty(message = "A descrição da tarefa é obrigatória")
    @Length(min=3, max=60, message="O tamanho da tarefa é inválido")
    String description

    @NotNull(message="A data da tarefa é obrigatória")
    @FutureOrPresent(message="A data da tarefa não pode estar no passado")
    LocalDate whenToDo

    boolean done = false

    @ManyToOne
    @JoinColumn(name="app_user_id")
//    @NotNull(message = "O usuário da tarefa é obrigatório")
    @JsonIgnore
    AppUser appUser


}
