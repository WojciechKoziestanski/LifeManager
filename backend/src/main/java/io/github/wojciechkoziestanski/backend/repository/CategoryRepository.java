package io.github.wojciechkoziestanski.backend.repository;

import io.github.wojciechkoziestanski.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
