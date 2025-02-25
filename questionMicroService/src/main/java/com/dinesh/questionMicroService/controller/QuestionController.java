package com.dinesh.questionMicroService.controller;


import com.dinesh.questionMicroService.model.Question;
import com.dinesh.questionMicroService.model.QuestionWrapper;
import com.dinesh.questionMicroService.model.Response;
import com.dinesh.questionMicroService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class QuestionController {
    @Autowired
    QuestionService services;
    @Autowired
    Environment environment;

    @GetMapping("/questions")
    public List<Question> getQuestion() {
        return services.getQuestion();
    }

    @GetMapping("/questions/{id}")
    public Optional<Question> getQuestionById(@PathVariable int id) {
        return services.getQuestionById(id);
    }

    @PostMapping("/questions")
    public String addQuestions(@RequestBody Question question) {
        services.addQuestions(question);
        return "Added successfully";
    }

    @GetMapping("/category/{category}")
    public List<Question> getByCategory(@PathVariable String category) {
        return services.getByCategory(category);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam int num){
        return services.getQuestionForQuiz(category,num);

    }
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionId){
        System.out.println(environment.getProperty("local.server.port"));
        return services.getQuestions(questionId);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return services.getScoure(responses);
    }
}
