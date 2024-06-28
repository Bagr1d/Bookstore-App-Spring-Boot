package ksi.springbooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ksi.springbooks.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
