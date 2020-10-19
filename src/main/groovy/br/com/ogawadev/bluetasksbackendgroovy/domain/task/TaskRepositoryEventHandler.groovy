package br.com.ogawadev.bluetasksbackendgroovy.domain.task

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component

@Component
@RepositoryEventHandler(Task.class)
class TaskRepositoryEventHandler {

    TaskRepository taskRepository

    @Autowired
    TaskRepositoryEventHandler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    void handle(Task task) throws DuplicatedTaskException {
        Task taskDB = taskRepository.findByDescription(task.description)
        boolean  duplicate = false
        if(taskDB != null) {
            if((task.id == null || task.id == 0) && task.description.equals(taskDB.description)) {
                duplicate = true
            }

            if((task.id != null && task.id > 0) && !task.id.equals(taskDB.id)) {
                duplicate = true
            }
        }

        if(duplicate) {
            throw new DuplicatedTaskException("Já existe uma tarefa com esta descrição")
        }
    }


}
