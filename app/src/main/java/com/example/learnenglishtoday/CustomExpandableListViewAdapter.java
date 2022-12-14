package com.example.learnenglishtoday;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> list_group;
    HashMap<String, List<String>> list_child;

    CustomExpandableListViewAdapter(Context context, List<String> list_group, HashMap<String, List<String>> list_child) {
        this.context = context;
        this.list_group = list_group;
        this.list_child = list_child;
    }


    @Override
    public int getGroupCount() {
        return this.list_group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.list_child.get(this.list_group.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.list_group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list_child.get(this.list_group.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group_title = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_group_layout, null);

        }

        if (groupPosition == 11) {
            ProgressBar test_progressbar = convertView.findViewById(R.id.test_progressbar);
            TextView progressbar_text = convertView.findViewById(R.id.progress_text);
            progressbar_text.setVisibility(View.VISIBLE);
            test_progressbar.setVisibility(View.VISIBLE);

            progressbar_text.setText(Float.toString(getOverallAverageFromSharedPreferences())+"%");
            test_progressbar.setProgress((int) getOverallAverageFromSharedPreferences());
        } else {
            ProgressBar test_progressbar = convertView.findViewById(R.id.test_progressbar);
            TextView progressbar_text = convertView.findViewById(R.id.progress_text);
            progressbar_text.setVisibility(View.GONE);
            test_progressbar.setVisibility(View.GONE);
        }
        TextView group_textview = convertView.findViewById(R.id.group_textview);
        group_textview.setText(group_title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String child_titles = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_child_layout, null);
        }

        if (groupPosition == 11) {
            ProgressBar test_progressbar = convertView.findViewById(R.id.test_progressbar);
            TextView progressbar_text = convertView.findViewById(R.id.progress_text);
            progressbar_text.setVisibility(View.VISIBLE);
            test_progressbar.setVisibility(View.VISIBLE);

            test_progressbar.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);

            if(childPosition==0){
                progressbar_text.setText(Float.toString(geta1AverageFromSharedPreferences())+"%");
                test_progressbar.setProgress((int)geta1AverageFromSharedPreferences());
            }else if(childPosition==1){
                progressbar_text.setText(Float.toString(geta2AverageFromSharedPreferences())+"%");
                test_progressbar.setProgress((int)geta2AverageFromSharedPreferences());
            }else if(childPosition==2){
                progressbar_text.setText(Float.toString(getb1AverageFromSharedPreferences())+"%");
                test_progressbar.setProgress((int)getb1AverageFromSharedPreferences());
            }else if(childPosition==3){
                progressbar_text.setText(Float.toString(getb2AverageFromSharedPreferences())+"%");
                test_progressbar.setProgress((int)getb2AverageFromSharedPreferences());
            }else if(childPosition==4){
                progressbar_text.setText(Float.toString(getc1AverageFromSharedPreferences())+"%");
                test_progressbar.setProgress((int)getc1AverageFromSharedPreferences());
            }else if(childPosition==5){
                progressbar_text.setText(Float.toString(getc2AverageFromSharedPreferences())+"%");
                test_progressbar.setProgress((int)getc2AverageFromSharedPreferences());
            }
        } else {
            ProgressBar test_progressbar = convertView.findViewById(R.id.test_progressbar);
            TextView progressbar_text = convertView.findViewById(R.id.progress_text);
            progressbar_text.setVisibility(View.GONE);
            test_progressbar.setVisibility(View.GONE);
        }

        TextView child_textview = convertView.findViewById(R.id.child_textview);
        child_textview.setText(child_titles);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public float getOverallAverageFromSharedPreferences() {

        float a1_average;
        float a2_average;
        float b1_average;
        float b2_average;
        float c1_average;
        float c2_average;
        float test_overall_average;

        SharedPreferences setting_a1 = context.getSharedPreferences("a1_average", context.MODE_PRIVATE);
        a1_average = setting_a1.getFloat("a1_average", 0);

        SharedPreferences setting_a2 = context.getSharedPreferences("a2_average", context.MODE_PRIVATE);
        a2_average = setting_a2.getFloat("a2_average", 0);

        SharedPreferences setting_b1 = context.getSharedPreferences("b1_average", context.MODE_PRIVATE);
        b1_average = setting_b1.getFloat("b1_average", 0);

        SharedPreferences setting_b2 = context.getSharedPreferences("b2_average", context.MODE_PRIVATE);
        b2_average = setting_b2.getFloat("b2_average", 0);

        SharedPreferences setting_c1 = context.getSharedPreferences("c1_average", context.MODE_PRIVATE);
        c1_average = setting_c1.getFloat("c1_average", 0);

        SharedPreferences setting_c2 = context.getSharedPreferences("c2_average", context.MODE_PRIVATE);
        c2_average = setting_c2.getFloat("c2_average", 0);

        test_overall_average = (a1_average + a2_average + b1_average + b2_average + c1_average + c2_average) / 600 * 100;

        return test_overall_average;
    }

    public float geta1AverageFromSharedPreferences(){
        float a1_average;
        SharedPreferences setting_a1 = context.getSharedPreferences("a1_average", context.MODE_PRIVATE);
        a1_average = setting_a1.getFloat("a1_average", 0);
        return a1_average;
    }
    public float geta2AverageFromSharedPreferences(){
        float a2_average;
        SharedPreferences setting_a2 = context.getSharedPreferences("a2_average", context.MODE_PRIVATE);
        a2_average = setting_a2.getFloat("a2_average", 0);
        return a2_average;
    }
    public float getb1AverageFromSharedPreferences(){
        float b1_average;
        SharedPreferences setting_b1 = context.getSharedPreferences("b1_average", context.MODE_PRIVATE);
        b1_average = setting_b1.getFloat("b1_average", 0);
        return b1_average;
    }
    public float getb2AverageFromSharedPreferences(){
        float b2_average;
        SharedPreferences setting_b2 = context.getSharedPreferences("b2_average", context.MODE_PRIVATE);
        b2_average = setting_b2.getFloat("b2_average", 0);

        return b2_average;
    }
    public float getc1AverageFromSharedPreferences(){
        float c1_average;
        SharedPreferences setting_c1 = context.getSharedPreferences("c1_average", context.MODE_PRIVATE);
        c1_average = setting_c1.getFloat("c1_average", 0);
        return c1_average;
    }
    public float getc2AverageFromSharedPreferences(){
        float c2_average;
        SharedPreferences setting_c2 = context.getSharedPreferences("c2_average", context.MODE_PRIVATE);
        c2_average = setting_c2.getFloat("c2_average", 0);
        return c2_average;
    }
}

class ExpandableListData{
    public  static HashMap <String, List<String>> getData(){
        HashMap<String,List<String>> expandableList = new LinkedHashMap<>();



        List<String> englishTenses = new ArrayList<String>();
        englishTenses.add("Present Simple/Hi???n t???i ????n");
        englishTenses.add("Present Continuous/Hi???n t???i ti???p di???n");
        englishTenses.add("Past Simple/Qu?? kh??? ????n");
        englishTenses.add("Past Continuous/Qu?? kh??? ti???p di???n");
        englishTenses.add("Future Simple/T????ng lai ????n");
        englishTenses.add("Future Continuous/T????ng lai ti???p di???n");
        englishTenses.add("Present Perfect Simple/Hi???n t???i ho??n th??nh");
        englishTenses.add("Present Perfect Continuous/Hi???n t???i ho??n th??nh ti???p di???n");
        englishTenses.add("Past Perfect/Qu?? kh??? ho??n th??nh");
        englishTenses.add("Past Perfect Continuous/Qu?? kh??? ho??n th??nh ti???p di???n");
        englishTenses.add("Future Perfect/T????ng lai ho??n th??nh");
        englishTenses.add("Future Perfect Continuous/T????ng lai ho??n th??nh ti???p di???n");

        List<String> englishGrammarTest = new ArrayList<>();
        englishGrammarTest.add("Level A1");
        englishGrammarTest.add("Level A2");
        englishGrammarTest.add("Level B1");
        englishGrammarTest.add("Level B2");
        englishGrammarTest.add("Level C1");
        englishGrammarTest.add("Level C2");

        List<String> idioms = new ArrayList<>();
        idioms.add("Idioms Metaphors and Similes/Th??nh ng??? ???n d??? v?? so s??nh");

        List<String> relativeClauses = new ArrayList<String>();
        relativeClauses.add("Define Relative Clauses/M???nh ????? quan h??? x??c ?????nh");
        relativeClauses.add("Non-Define Relative Clauses/M???nh ????? quan h??? kh??ng x??c ?????nh");

        List<String> activePassive = new ArrayList<>();
        activePassive.add("Active/Passive");
        activePassive.add("Personal/Impersonal Passive");

        List<String> conditionals = new ArrayList<>();
        conditionals.add("Zero Conditional/C??u ??i???u ki???n lo???i 0");
        conditionals.add("1st Conditional/C??u ??i???u ki???n lo???i 1");
        conditionals.add("2nd Conditional/C??u ??i???u ki???n lo???i 2");
        conditionals.add("3rd Conditional/C??u ??i???u ki???n lo???i 3");
        conditionals.add("Mix Conditional/C??u ??i???u ki???n h???n h???p");

        List<String> ReportedSpeech = new ArrayList<>();
        ReportedSpeech.add("Direct,Indirect Speech/L???i n??i tr???c ti???p, gi??n ti???p");
        ReportedSpeech.add("Reporting Questions/C??u h???i t?????ng thu???t");
        ReportedSpeech.add("Reporting Commands,Request/C??u y??u c???u, ????? ngh???");

        List<String> infinitivesGerund = new ArrayList<>();
        infinitivesGerund.add("Infinitives or Gerund/?????ng t??? nguy??n m???u ho???c Danh ?????ng t???");

        List<String> verbs = new ArrayList<>();
        verbs.add("Full Verb/?????ng t???");
        verbs.add("Auxiliary Verbs/Tr??? ?????ng t???");
        verbs.add("Modal Auxiliary Verbs/?????ng t??? khi???m khuy???t");

        List<String> articles = new ArrayList<>();
        articles.add("Definite & Indefinite Article/M???o t??? x??c ?????nh v?? kh??ng x??c ?????nh");

        List<String> possessives = new ArrayList<>();
        possessives.add("Adjectives & Pronoun/ T??nh t??? & ?????i t???");


        List<String> partsofSpeech= new ArrayList<String>();
            partsofSpeech.add("Verb, Noun, Pronoun, Adjective,.../?????ng t???, Danh t???, ?????i t???, T??nh t???,...");
        List<String> usemanual = new ArrayList<>();
        usemanual.add("User manual/H?????ng d???n s??? d???ng");


        List<String> adverbs = new ArrayList<>();
        adverbs.add("Adverbs of Manner");
        adverbs.add("Adverbs of Place");
        adverbs.add("Adverbs of Time");
        adverbs.add("Adverbs of Frequency");




        expandableList.put("PARTS OF SPEECH/C??C PH???N C???A B??I PH??T BI???U", partsofSpeech);
        expandableList.put("TWELVE VERB TENSES/12 TH?? C?? B???N", englishTenses);
        expandableList.put("ARTICLES/M???O T???", relativeClauses);
        expandableList.put("POSSESSIVES/S??? H???U C??CH", possessives);
        expandableList.put("CONDITIONALS/C??U ??I???U KI???N", conditionals);
        expandableList.put("INFINITIVE & GERUNDS/?????NG T??? NGUY??N M???U V?? DANH ?????NG T???", infinitivesGerund);
        expandableList.put("RELATIVE CLAUSES/M???NH ????? QUAN H???", relativeClauses);
        expandableList.put("REPORTED SPEECH/C??U T?????NG THU???T", ReportedSpeech);
        expandableList.put("VERBS/?????NG T???", verbs);
        expandableList.put("IDIOMS/TH??NH NG???", idioms);
        expandableList.put("USER MANUAL/H?????NG D???N S??? D???NG", usemanual);
        expandableList.put("GRAMMAR TESTS/KI???M TRA NG??? PH??P", englishGrammarTest);

        return expandableList;
    }

}