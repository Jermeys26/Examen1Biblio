package spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.app.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
