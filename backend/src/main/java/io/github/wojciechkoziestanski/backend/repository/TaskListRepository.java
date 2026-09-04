package io.github.wojciechkoziestanski.backend.repository;

import io.github.wojciechkoziestanski.backend.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
