package com.dinesh.quizMicroService.controller;


import com.dinesh.quizMicroService.model.QuestionWrapper;
import com.dinesh.quizMicroService.model.QuizDto;
import com.dinesh.quizMicroService.model.Response;
import com.dinesh.quizMicroService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService service;
       @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return service.createQuiz(quizDto.getCategory(),quizDto.getNum(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return service.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return service.calculateResult(id,responses);
    }
}
