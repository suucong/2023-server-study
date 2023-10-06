package com.sujin.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{

    // SpringDataJpa에서 제공하는 기본 메서드만으로 해결 가능하지만. Query가
    // 가독성이 좋기 때문에 선택해서 사용하면 된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
