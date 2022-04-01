package com.fundamentsproyect.demo.repository;

import com.fundamentsproyect.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {
}
