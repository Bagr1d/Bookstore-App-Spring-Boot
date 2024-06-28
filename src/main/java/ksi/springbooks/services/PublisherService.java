package ksi.springbooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ksi.springbooks.models.Publisher;
import ksi.springbooks.repositories.PublisherRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository repository;

    public List<Publisher> findAll() {
        return repository.findAll();
    }

    public void save(Publisher publisher) {
        repository.save(publisher);
    }

    public Optional<Publisher> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
