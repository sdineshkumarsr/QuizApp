package com.dinesh.questionMicroService.service;



import com.dinesh.questionMicroService.model.Question;
import com.dinesh.questionMicroService.model.QuestionWrapper;
import com.dinesh.questionMicroService.model.Response;
import com.dinesh.questionMicroService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class QuestionService {
    @Autowired
    QuestionRepository repo;
    public List<Question> getQuestion() {
        return repo.findAll();
    }

    public Optional<Question> getQuestionById(int id) {
        return repo.findById(id);
    }

    public void addQuestions(Question question) {
        repo.save(question);
    }

    public List<Question> getByCategory(String category) {
        return  repo.findByCategory(category);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, int num) {
        List<Integer> questions=repo.findRandomByCategory(category,num);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionId) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for (Integer id:questionId){
            questions.add(repo.findById(id).get());
        }

        for (Question question:questions){
            QuestionWrapper wrapper=new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScoure(List<Response> responses) {
        int right=0;

        for(Response response:responses){
            Question question =repo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRight_answer())){
                right++;

            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
