package com.example.learnenglishtoday;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class GrammarTestActivity extends AppCompatActivity {

    private List<QuestionModel> quesitonBundle;
    private TextView question_text_tv;
    private TextView question_counter_tv;
    private TextView level_tv;
    private TextView test_description_tv;
    private RadioGroup option_group;
    private RadioButton option_a;
    private RadioButton option_b;
    private RadioButton option_c;
    private Button submit_btn;
    String user;
    DBReportHelper dbTest;
    DBTestHelper db;
    private  int current_question_index=0;

    //
    private  int test_points_counter=0;
    private float test_current_average_counter=0f;
    private TextView test_points_tv;
    private TextView test_current_average_tv;

    private float a1_average;
    private float a2_average;
    private float b1_average;
    private float b2_average;
    private float c1_average;
    private float c2_average;
    private float test_overall_average;
    private TextView report_total_question_tv, report_answered_correct_tv, report_answered_wrong_tv, report_test_points_tv, report_current_average_tv, report_overall_average_tv;
    private int setLevelForQuestion_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_test);
        dbTest = new DBReportHelper(this);
        db = new DBTestHelper(this);
        typeCastObject();
        setLevelForQuestion();
        setQuestionElementsOnViews(current_question_index);
        submit_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (option_group.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(GrammarTestActivity.this, "Please choose the answer!!!", Toast.LENGTH_SHORT).show();
                } else {

                    if(isCorrectAnswerSelect()){
                        Toast.makeText(GrammarTestActivity.this, "The answer is right", Toast.LENGTH_SHORT).show();

                        test_points_counter++;
                        test_current_average_counter = (float) 100/quesitonBundle.size()*test_points_counter;
                        gotNext();
                    }else{
                        Toast.makeText(GrammarTestActivity.this, "The answer is wrong", Toast.LENGTH_SHORT).show();

                        gotNext();
                    }
                }
            }
            });
    }
    private void gotNext() {
        current_question_index++;
        if(current_question_index<quesitonBundle.size()){
            setQuestionElementsOnViews(current_question_index);
        }else {
            Toast.makeText(this, "Test Finished", Toast.LENGTH_SHORT).show();
            submit_btn.setEnabled(false);
            test_end_show_result_dialog(setLevelForQuestion_TAG);
        }
    }

    @SuppressLint("SetTextI18n")
    private void test_end_show_result_dialog(int setLevelForQuestion_tag) {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) user = "";
        else user = bundle.getString("user");
        if(setLevelForQuestion_tag==0){
            setDataToSharePreferences("a1_average",test_current_average_counter);
            dbTest.addTestResult(GrammarTestActivity.this, user,"A1", test_current_average_counter);
        }else if(setLevelForQuestion_tag==1){
            setDataToSharePreferences("a2_average", test_current_average_counter);
            dbTest.addTestResult(GrammarTestActivity.this, user,"A2", test_current_average_counter);
        }else if(setLevelForQuestion_tag==2){
            setDataToSharePreferences("b1_average", test_current_average_counter);
            dbTest.addTestResult(GrammarTestActivity.this, user,"B1", test_current_average_counter);
        }else if(setLevelForQuestion_tag==3) {
            setDataToSharePreferences("b2_average", test_current_average_counter);
            dbTest.addTestResult(GrammarTestActivity.this, user,"B2", test_current_average_counter);
        }else if(setLevelForQuestion_tag==4) {
            setDataToSharePreferences("c1_average", test_current_average_counter);
            dbTest.addTestResult(GrammarTestActivity.this, user,"C1", test_current_average_counter);
        }else if(setLevelForQuestion_tag==5) {
            setDataToSharePreferences("c2_average", test_current_average_counter);
            dbTest.addTestResult(GrammarTestActivity.this, user,"C2", test_current_average_counter);
        }

        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.grammar_test_result_layout,null,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Test Report");
        builder.setView(view);

        report_total_question_tv=view.findViewById(R.id.tv_total_question);
        report_answered_correct_tv=view.findViewById(R.id.tv_answered_correct);
        report_answered_wrong_tv=view.findViewById(R.id.tv_answered_wrong);
        report_test_points_tv=view.findViewById(R.id.tv_test_points);
        report_current_average_tv=view.findViewById(R.id.tv_current_average);
        report_overall_average_tv=view.findViewById(R.id.tv_overall_average);


        report_total_question_tv.setText(Integer.toString(test_points_counter));
        report_answered_wrong_tv.setText(Integer.toString(test_points_counter));
        report_answered_wrong_tv.setText(Integer.toString(quesitonBundle.size()- test_points_counter));
        report_test_points_tv.setText(Integer.toString(test_points_counter));
        report_current_average_tv.setText(Float.toString(test_current_average_counter));
        report_overall_average_tv.setText(Float.toString(getDataFromSharedPreferences()));

        Dialog dialog= builder.create();
        dialog.show();
    }

    private boolean isCorrectAnswerSelect() {
        String answer="";
        RadioButton selected_option = findViewById(option_group.getCheckedRadioButtonId());

        if(selected_option==option_a) {
            answer = "a";
        }else if(selected_option==option_b){
            answer = "b";
        }else if(selected_option==option_c){
            answer = "c";
        }
        return quesitonBundle.get(current_question_index).is_answer_correct(answer);
    }


    private void setQuestionElementsOnViews(int current_question_index) {

        question_text_tv.setText(quesitonBundle.get(current_question_index).getQuetion_text());
        question_counter_tv.setText(Integer.toString(current_question_index+1) + ":");
        option_a.setText(quesitonBundle.get(current_question_index).getOption_a());
        option_b.setText(quesitonBundle.get(current_question_index).getOption_b());
        option_c.setText(quesitonBundle.get(current_question_index).getOption_c());
        option_group.clearCheck();

        test_points_tv.setText("Test points:" + Integer.toString(test_points_counter));
        test_current_average_tv.setText("Current Average:" + Float.toString(test_current_average_counter));
    }

    private void setLevelForQuestion(){
        setLevelForQuestion_TAG = getIntent().getIntExtra("child_id", 0);
        switch (setLevelForQuestion_TAG){
            case 0:
                newLevel_a1();
                level_tv.setText("Level A1");
                test_description_tv.setText("This Test contains 25 question, each curries equal points make sure to choose the best option before submitting");
                break;
            case 1:
                newLevel_a2();
                level_tv.setText("Level A2");
                test_description_tv.setText("This Test contains 25 question, each curries equal points make sure to choose the best option before submitting");
                break;
            case 2:
                newLevel_b1();
                level_tv.setText("Level B1");
                test_description_tv.setText("This Test contains 25 question, each curries equal points make sure to choose the best option before submitting");
                break;
            case 3:
                newLevel_b2();
                level_tv.setText("Level B2");
                test_description_tv.setText("This Test contains 25 question, each curries equal points make sure to choose the best option before submitting");
                break;
            case 4:
                newLevel_c1();
                level_tv.setText("Level C1");
                test_description_tv.setText("This Test contains 30 question, each curries equal points make sure to choose the best option before submitting");
                break;
            case 5:
                newLevel_c2();
                level_tv.setText("Level C2");
                test_description_tv.setText("This Test contains 30 question, each curries equal points make sure to choose the best option before submitting");
                break;
        }

    }
    private void newLevel_a1(){
        List<QuestionModel> a = db.getAllQuestionLevel("'A1'");
        quesitonBundle = a;
    }
    private void newLevel_a2(){
        List<QuestionModel> a = db.getAllQuestionLevel("'A2'");
        quesitonBundle = a;
    }
    private void newLevel_b1(){
        List<QuestionModel> a = db.getAllQuestionLevel("'B1'");
        quesitonBundle = a;
    }
    private void newLevel_b2(){
        List<QuestionModel> a = db.getAllQuestionLevel("'B2'");
        quesitonBundle = a;
    }
    private void newLevel_c1(){
        List<QuestionModel> a = db.getAllQuestionLevel("'C1'");
        quesitonBundle = a;
    }
    private void newLevel_c2(){
        List<QuestionModel> a = db.getAllQuestionLevel("'C2'");
        quesitonBundle = a;
    }

    private void typeCastObject() {
        quesitonBundle= new ArrayList<QuestionModel>();
        question_text_tv= findViewById(R.id.question_text);
        question_counter_tv=findViewById(R.id.question_counter);
        option_group=findViewById(R.id.option_group);
        option_a=findViewById(R.id.option_a);
        option_b=findViewById(R.id.option_b);
        option_c=findViewById(R.id.option_c);
        submit_btn=findViewById(R.id.btn_submit);
        level_tv= findViewById(R.id.level_textview);
        test_description_tv=findViewById(R.id.test_description_textview);

        test_points_tv=findViewById(R.id.test_points_textView);
        test_current_average_tv= findViewById(R.id.current_test_average_textView);

    }
    private void mCustomToast(int image){
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast_layout, null, false);
        GifImageView imageView = view.findViewById(R.id.custom_toast_image_view);
        imageView.setImageResource(image);

        Toast mToast = new Toast(this);
        mToast.setView(view );
        mToast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
    private void  setDataToSharePreferences(String level_name, float value){
        SharedPreferences setting = this.getSharedPreferences(level_name, this.MODE_PRIVATE);
        SharedPreferences.Editor editor= setting.edit();
        editor.putFloat(level_name, value);
        editor.commit();
    }
    private float getDataFromSharedPreferences(){
        SharedPreferences setting_a1 = this.getSharedPreferences("a1_average", this.MODE_PRIVATE);
        a1_average= setting_a1.getFloat("a1_average", 0);

        SharedPreferences setting_a2 = this.getSharedPreferences("a2_average", this.MODE_PRIVATE);
        a2_average= setting_a2.getFloat("a2_average", 0);

        SharedPreferences setting_b1 = this.getSharedPreferences("b1_average", this.MODE_PRIVATE);
        b1_average= setting_b1.getFloat("b1_average", 0);

        SharedPreferences setting_b2 = this.getSharedPreferences("b2_average", this.MODE_PRIVATE);
        b2_average= setting_b2.getFloat("b2_average", 0);

        SharedPreferences setting_c1 = this.getSharedPreferences("c1_average", this.MODE_PRIVATE);
        c1_average= setting_c1.getFloat("c1_average", 0);

        SharedPreferences setting_c2 = this.getSharedPreferences("c2_average", this.MODE_PRIVATE);
        c2_average= setting_c2.getFloat("c2_average", 0);

        test_overall_average = (a1_average+ a2_average +b1_average+ b2_average + c1_average+ c2_average)/600*100;

        return test_overall_average;
    }
}