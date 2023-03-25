package com.azubike.ellipsis.spring_cache.repository;

import com.azubike.ellipsis.spring_cache.domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
  @Transactional
  @Modifying
  @Query("update Book u set u.name=:name where u.id=:id")
  int updateName(@Param("id") long id, @Param("name") String name);
}
