package br.com.ogawadev.bluetasksbackendgroovy.domain.task

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {

    Task findByDescription(String description)
}