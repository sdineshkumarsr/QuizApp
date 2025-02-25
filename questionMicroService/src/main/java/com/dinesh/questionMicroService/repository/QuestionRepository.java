package com.dinesh.questionMicroService.repository;


import com.dinesh.questionMicroService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

   @Query(value = "SELECT q.id FROM questions q WHERE q.category= :category ORDER BY RAND() LIMIT :num",nativeQuery = true)
    List<Integer> findRandomByCategory(String category, int num);
}
