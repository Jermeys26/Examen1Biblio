package spring.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.app.entity.Book;
import spring.app.repository.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book store(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void destroy(int id) {
        bookRepository.deleteById(id);
    }

    public void update(Book book) {
        Book b = bookRepository.findById(book.getId()).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        b.setDate(book.getDate());
        b.setLibrary(book.getLibrary());
        bookRepository.save(book);
    }
}
