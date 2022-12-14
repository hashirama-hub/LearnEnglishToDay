package com.example.learnenglishtoday;

public class QuestionModel {

    private String quetion_text;
    private  String option_a;
    private  String option_b;
    private  String option_c;

    private String correct_option;
    private boolean is_score_already_given;

    public QuestionModel(String quetion_text, String option_a, String option_b, String option_c, String correct_option) {
        this.quetion_text = quetion_text;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.correct_option = correct_option;
        this.is_score_already_given=false;
    }

    public String getQuetion_text() {
        return quetion_text;
    }

    public void setQuetion_text(String quetion_text) {
        this.quetion_text = quetion_text;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    public boolean isIs_score_already_given() {
        return is_score_already_given;
    }

    public void setIs_score_already_given(boolean is_score_already_given) {
        this.is_score_already_given = is_score_already_given;
    }


    public boolean is_answer_correct(String selected_option){
        return (correct_option.equals(selected_option));
    }

}
