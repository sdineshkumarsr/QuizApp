package com.dinesh.quizMicroService.model;

public class QuizDto {
   private String category;
    private Integer num;
    private String title;

    public QuizDto() {
    }

    public QuizDto(String category, Integer num, String title) {
        this.category = category;
        this.num = num;
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "category='" + category + '\'' +
                ", num=" + num +
                ", title='" + title + '\'' +
                '}';
    }
}
