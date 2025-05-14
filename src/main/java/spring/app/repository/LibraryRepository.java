package spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.app.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    
}
