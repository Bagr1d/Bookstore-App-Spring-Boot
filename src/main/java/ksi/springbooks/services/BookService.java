package ksi.springbooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ksi.springbooks.models.Book;
import ksi.springbooks.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> findAll() {
        return repository.findByOrderByTitleAsc();
    }

    public void save(Book book) {
        repository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
