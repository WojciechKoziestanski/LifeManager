package io.github.wojciechkoziestanski.backend.repository;

import io.github.wojciechkoziestanski.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
