package org.book.backend_book.repository;

import org.book.backend_book.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{title : ?0}")
    List<Book> getBooksByTitle(String title);

    @Query("{'title': { $regex: ?0, $options: 'i' }}")
    List<Book> findByPartialTitle(String title);


}
