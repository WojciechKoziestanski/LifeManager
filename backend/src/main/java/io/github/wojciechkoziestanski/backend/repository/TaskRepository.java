package io.github.wojciechkoziestanski.backend.repository;

import io.github.wojciechkoziestanski.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
