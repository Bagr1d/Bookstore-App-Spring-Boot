package ksi.springbooks.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idb;

    @NotEmpty(message = "Title is required")
    private String title;

    @ManyToOne
    @NotNull(message = "Publisher is required")
    private Publisher publisher;

    @ManyToOne
    @NotNull(message = "Category is required")
    private Category category;

    @ManyToOne
    @NotNull(message = "Author is required")
    private Author author;

    // Getters and setters
    public Long getIdb() {
        return idb;
    }

    public void setIdb(Long idb) {
        this.idb = idb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
