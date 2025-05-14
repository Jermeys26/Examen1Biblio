package spring.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.app.entity.Library;
import spring.app.repository.LibraryRepository;

@Service
public class LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library store(Library library) {
        return libraryRepository.save(library);
    }

    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    public Library findById(int id) {
        return libraryRepository.findById(id).orElse(null);
    }

    public void update(Library library) {
        libraryRepository.save(library);
    }

    public void destroy(int id) {
        Library library = libraryRepository.findById(id).orElse(null);

        if (library != null && (library.getBooks().isEmpty())) {
            libraryRepository.deleteById(id);
        } else {
            throw new RuntimeException("NO SE PUEDE ELIMINAR PORQUE HAY LIBROS ASIGNADOS");
        }
    }
}
