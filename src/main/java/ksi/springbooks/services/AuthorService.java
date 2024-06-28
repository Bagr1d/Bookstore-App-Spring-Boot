package ksi.springbooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ksi.springbooks.models.Author;
import ksi.springbooks.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    public List<Author> findAll() {
        return repository.findAll();
    }

    public void save(Author author) {
        repository.save(author);
    }

    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
