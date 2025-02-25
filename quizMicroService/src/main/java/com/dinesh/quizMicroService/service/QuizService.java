package com.dinesh.quizMicroService.service;


import com.dinesh.quizMicroService.fing.QuizInterface;
import com.dinesh.quizMicroService.model.QuestionWrapper;
import com.dinesh.quizMicroService.model.Quiz;
import com.dinesh.quizMicroService.model.Response;
import com.dinesh.quizMicroService.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository repo;
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String category, int num, String title) {
          List<Integer> questions=quizInterface.getQuestionForQuiz(category,num).getBody();
          Quiz quiz=new Quiz();
          quiz.setTitle(title);
          quiz.setQuestionIds(questions);
          repo.save(quiz);
          return new ResponseEntity<>("Success",HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz=repo.findById(id).get();
        List<Integer> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestions(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
    ResponseEntity<Integer> score=quizInterface.getScore(responses);
    return score;
    }
}
