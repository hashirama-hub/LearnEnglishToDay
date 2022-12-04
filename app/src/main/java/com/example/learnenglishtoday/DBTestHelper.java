package com.example.learnenglishtoday;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class DBTestHelper extends SQLiteOpenHelper {
    public static String DB = "test.db";

    public DBTestHelper(Context context) {
        super(context, DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create table tests(level TEXT, question TEXT, optiona TEXT, optionb TEXT, optionc TEXT, correct TEXT, PRIMARY KEY(question))");
        createQuestion(myDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop table if exists tests");
        createQuestion(myDb);
    }

    public void createQuestion(SQLiteDatabase myDb) {
        ArrayList<String[]> map = new ArrayList<String[]>();
        map.add(new String[]{"A1", "I don't know ....... people", "many", "much", "a lot", "a"});
        map.add(new String[]{"A1", "We live near.......the river", "of", "from", "-", "c"});
        map.add(new String[]{"A1", "He says that he must have.......", "the all from it", "all it", "it all", "c"});
        map.add(new String[]{"A1", "John is a good worker: He works very......", "hardly", "hard", "good", "b"});
        map.add(new String[]{"A1", "Let's...... a party", "do", "have", "set", "b"});
        map.add(new String[]{"A1", "The radio's much too loud:please turn it.....", "low", "up", "down", "c"});
        map.add(new String[]{"A1", "How much time do you......your homework?", "make it", "bring to", "spend on", "c"});
        map.add(new String[]{"A1", "My father is ....... man", "an old", "a old", "not young", "a"});
        map.add(new String[]{"A1", "Millions of cigarettes.....every year", "is smoke", "are smoking", "are smoked", "c"});
        map.add(new String[]{"A1", "Gold, as well as silver.....in price, he said.", "has fallen", "have fallen", "fall", "a"});
        map.add(new String[]{"A1", "They quarreled......the choice of a house.", "on", "over", "for", "b"});
        map.add(new String[]{"A1", "Fred was a really silly boy when we were in high-school. I still remember.....very stupid questions.", "his asking", "him to ask", "asking him", "a"});
        map.add(new String[]{"A1", "........ I like the Rolling Stones.", "No need to say", "Don't need to say", "Needless to say", "c"});
        map.add(new String[]{"A1", "If he had not given me advice, I......again.", "would fail", "wouldn't fail", "would have failed", "c"});
        map.add(new String[]{"A1", "What.....! The rain has not stopped all day.", "the weather", "weather", "a weather", "b"});
        map.add(new String[]{"A1", "Every morning I wash my face and clean my teeth by....", "I", "mine", "myself", "c"});
        map.add(new String[]{"A1", "Nam was absent from class yesterday....he felt sick.", "so", "because", "although", "b"});
        map.add(new String[]{"A1", "Tom has a computer,....he doesn’t use it.", "but", "or", "as", "a"});
        map.add(new String[]{"A1", "Water is......than tequila.", "healthier", "more healthier", "healthiest", "a"});
        map.add(new String[]{"A1", "I was ill yesterday but I am .....today", "better", "gooder", "weller", "a"});
        map.add(new String[]{"A1", "Lan is in my class. She is my......", "classroom", "classmate", "neighbor", "b"});
        map.add(new String[]{"A1", "What does he look.........? – He is fat and short.", "Like", "at", "for", "a"});
        map.add(new String[]{"A1", ".....is the matter with you? – I have a toothache.", "What", "How", "When", "a"});
        map.add(new String[]{"A1", "Peter......to Linda about his vacation last week.", "talks", "talked", "talking", "b"});
        map.add(new String[]{"A1", "What are you.......? -I'm playing soccer.", "do", "to do", "doing", "c"});
        map.add(new String[]{"A2", "They went ________ ship from Liverpool to Dublin.", "by", "on", "with", "a"});
        map.add(new String[]{"A2", "I preferred that she ________ here.", "stayed", "staying", "stay", "c"});
        map.add(new String[]{"A2", "Mike is very ________ of his new car.", "feel", "proud", "happy", "b"});
        map.add(new String[]{"A2", "When do you usually arrive ________ the office?", "to", "on", "at", "c"});
        map.add(new String[]{"A2", "Our people began this flight ________ 1973.", "at", "since", "in", "c"});
        map.add(new String[]{"A2", "- Do you have any pets? - Yes, I have ________ kittens.", "two small black", "small two black", "two black small", "a"});
        map.add(new String[]{"A2", "- Do you like tea? - ________.", "Much", "Yes, I do", "Is so", "b"});
        map.add(new String[]{"A2", "The bank is on ________ side of the street.", "other", "the other", "the rest", "b"});
        map.add(new String[]{"A2", "The boat ________ in the river last night", "sinks", "sank", "drowns", "b"});
        map.add(new String[]{"A2", "He ________ newspapers for ten years.", "is selling", "sells", "has been selling", "c"});
        map.add(new String[]{"A2", "It's ________ . Give it to them.", "theirs", "their's", "there's", "a"});
        map.add(new String[]{"A2", "________ a blackboard in the classroom?", "Is", "Is it", "Is there", "c"});
        map.add(new String[]{"A2", "What's ________ the radio this morning?", "about", "at", "on", "c"});
        map.add(new String[]{"A2", "Some people ________ eat meat.", "do never", "ever", "never", "c"});
        map.add(new String[]{"A2", "Which cinema ________?", "do you want to go to", "do you want to go to it", "do you want to go", "a"});
        map.add(new String[]{"A2", "He was ________ of all his money.", "stole", "robbed", "removed", "b"});
        map.add(new String[]{"A2", "- A: Have you got a lighter? - B: ________.", "Yes, I have", "Yes, I have got", "That's all right", "a"});
        map.add(new String[]{"A2", "________ you fond of jazz?", "is", "am", "are", "c"});
        map.add(new String[]{"A2", "The animals were afraid, but their keeper soon got them ________ control.", "over", "without", "under", "c"});
        map.add(new String[]{"A2", "Kate and Jenny have a brother. ________ Bill.", "Their name is", "Her name is", "His name is", "c"});
        map.add(new String[]{"A2", "He will not be ________ to vote in this year's election.", "old enough", "as old enough", "enough old", "a"});
        map.add(new String[]{"A2", "You can ________ my surprise when I heard the news.", "suppose", "see", "imagine", "c"});
        map.add(new String[]{"A2", "- There are two Olympic medalists entered in the competition. - How could Mike ever hope ________ ?", "winning", "to win", "that he", "c"});
        map.add(new String[]{"A2", "The reason ________ he's late is that he had an accident.", "which", "that", "why", "c"});
        map.add(new String[]{"A2", "There are three men ________ waiting outside.", "who", "they", "-", "c"});
        map.add(new String[]{"B1", "________ going to the party.", "Everybody are", "Every people is", "Everyone is", "c"});
        map.add(new String[]{"B1", "Her landlord gave her two months' ________ to quit.", "note", "notice", "allowance", "b"});
        map.add(new String[]{"B1", "They ________ their examinations at the end of the next month.", "take", "takes", "are going to take", "c"});
        map.add(new String[]{"B1", "When the house is not occupied, it is ________.", "rented", "timid", "vacant", "c"});
        map.add(new String[]{"B1", "Janet and Joe seem to get ________ very well together.", "on", "in", "by", "a"});
        map.add(new String[]{"B1", "Unfortunately, very few books published each year ________ toward improving men's relationship with each other.", "have a bad effect", "do a great deal of damage", "make a significant contribution", "c"});
        map.add(new String[]{"B1", "I don't like iced tea, and ________.", "she doesn't too", "either doesn't she", "neither does she", "c"});
        map.add(new String[]{"B1", "The ________ response I got from her really drove me crazy\", he sighed", "few", "a few", "little", "c"});
        map.add(new String[]{"B1", "You're ________ who noticed that.", "the single", "only one", "the only one", "c"});
        map.add(new String[]{"B1", "The Director has just gone on his ________ leave. He gets four weeks' holiday a year and he is taking it all at the same time. So if you wish to see him, come back in a month's time.", "annual", "temporary", "regular", "a"});
        map.add(new String[]{"B1", "I am ________ this essay right now.", "finish", "to finish", "finish to", "b"});
        map.add(new String[]{"B1", "________ people can live without any money.", "Few", "A few", "Littile", "a"});
        map.add(new String[]{"B1", " I don't think he would mind ________ there with me.", "going", "go to", "go", "a"});
        map.add(new String[]{"B1", " ________ he leaves or I leave.", "Only", "Unless", "Either", "c"});
        map.add(new String[]{"B1", " ________ to Professor Martin since last Monday?", "Did you talk", "Have you talked", "Had you talked", "c"});
        map.add(new String[]{"B1", "Tom doesn't like dancing, but I.........", "does", "did", "do", "c"});
        map.add(new String[]{"B1", "Listen! Somebody..........", "are sinigng", "sing", "is singing", "c"});
        map.add(new String[]{"B1", "They always get to school......... bus.", "by", "on", "in", "a"});
        map.add(new String[]{"B1", "We were eager to find......... the truth about her.", "through", "at", "out", "c"});
        map.add(new String[]{"B1", "More and more people are addicted........ games online.", "by", "to", "at", "b"});
        map.add(new String[]{"B1", "It says in the paper that electrical goods will be......... sale this month.", "form", "on", "in", "b"});
        map.add(new String[]{"B1", "He was putting......... his shirt when he heard a loud noise outside.", "back", "up", "on", "c"});
        map.add(new String[]{"B1", "You need a ..........to leave the country.", "passkey", "passport", "phototbook", "b"});
        map.add(new String[]{"B1", "Even with a degree, there’s no.........of work nowadays.", "guarantee", "security", "project", "a"});
        map.add(new String[]{"B1", "What was the final........... of the game?", "count", "mark", "score", "c"});
        map.add(new String[]{"B2", "If we are thinking of having a day in the country, I should like to listen to the weather ________.", "spell", "forecast", "recording", "b"});
        map.add(new String[]{"B2", "I bought a single ticket only on the bus, but my friend bought a ________ one.", "double", "new", "return", "c"});
        map.add(new String[]{"B2", "To get money for some purpose is to ________.", "raise it", "promote it", "donate it", "a"});
        map.add(new String[]{"B2", "He always wore a shirt with an open ________.", "colour", "collar", "top", "b"});
        map.add(new String[]{"B2", "A few of ________ are planning to drive to Florida during spring break.", "we girls", "us girls", "girls we", "b"});
        map.add(new String[]{"B2", "His performance was ________; the audience was delighted.", "unmarked", "faultless", "worthless", "b"});
        map.add(new String[]{"B2", "Who ________ this window? It's so cold here.", " is going to open", "has opened", "opening", "b"});
        map.add(new String[]{"B2", "No educational system is perfect. Each one has its ________.", "borders", "limitations", "limits", "b"});
        map.add(new String[]{"B2", "He ________ for a job for some weeks but he has not found one.", "is looking", "looks", "has been looking", "c"});
        map.add(new String[]{"B2", "I'd be careful in my dealings with her. I'm sure she's up to no ________.", "pretences", "good", "eel", "b"});
        map.add(new String[]{"B2", "No educational system is perfect. Each one has its ________.", "borders", "limitations", "limits", "b"});
        map.add(new String[]{"B2", "He ________ for a job for some weeks but he has not found one.", "is looking", "looks", "has been looking", "c"});
        map.add(new String[]{"B2", "I'd be careful in my dealings with her. I'm sure she's up to no ________.", "pretences", "good", "eel", "b"});
        map.add(new String[]{"B2", "The lost of a front tooth has left an unsightly ________ in her teeth.", "slot", "gap", "hole", "b"});
        map.add(new String[]{"B2", "After the game, the referee was interviewed ________ television.", "at", "on", "in", "b"});
        map.add(new String[]{"B2", "Please hurry or you will be late ________ school.", "to", "for", "in", "b"});
        map.add(new String[]{"B2", "I wouldn't believe what she says. She sounds very convincing but she's just a fast ________.", "talker", "eyes", "one", "a"});
        map.add(new String[]{"B2", "I'm very tired - I ________ all morning.", "works", "am working", "have been working", "c"});
        map.add(new String[]{"B2", "If it ________ so late we could have coffee.", "wasn't", "isn't", "not be", "c"});
        map.add(new String[]{"B2", "His parents made him ________ very hard.", "work", "to work", " work to", "a"});
        map.add(new String[]{"B2", "My friend ________ when the lesson started.", "hasn't arrived", "wasn't arrived", "hadn't arrived", "c"});
        map.add(new String[]{"B2", "His government insisted that he ________ until he finished his degree.", "stay", "stayed", "shall stay", "a"});
        map.add(new String[]{"B2", " I am glad so many people have passed the test. In fact, there were ________ who haven't.", "littile", "a little", "few", "c"});
        map.add(new String[]{"B2", "I am very ________ your society and should like to have more details.", "concerned about", "interested in", "involved with", "b"});
        map.add(new String[]{"B2", "He ________ this kind of music at all.", "doesn't like", "likes", "don't like", "a"});
        map.add(new String[]{"B2", "The assignment for Monday was to read ________ in your textbooks.", "chapter tenth", "chapter the tenth", "the tenth chapter", "c"});
        map.add(new String[]{"B2", "I knew him ________ I was child.", "untill", "when", "as", "b"});
        map.add(new String[]{"B2", "I couldn't understand what she was ________.", "telling", "speaking", "saying", "c"});
        map.add(new String[]{"C1", "The doctor gave the patient ________ examination to discover the cause of his collapse.", " a thorough", "a universal", "a whole", "a"});
        map.add(new String[]{"C1", " After the revolution, the people of Haiti ________ revenge on the secret police.", "made", "took", "had", "b"});
        map.add(new String[]{"C1", "We had ________ reached the church when we saw everyone leaving.", "quite", "rather", "almost", "c"});
        map.add(new String[]{"C1", " I find it difficult to talk to Cecilia because we have so ________ in common.", "few", "small", "little", "c"});
        map.add(new String[]{"C1", "Please go in. Mr Jones is free ________ you now.", "see", "will see", "to see", "c"});
        map.add(new String[]{"C1", "The coal is mined day ________ night by a system of shift work.", "through", "into", "and", "c"});
        map.add(new String[]{"C1", "He was unable to ________ his niece's wedding as he was ill.", "be present", "visit", "attend", "c"});
        map.add(new String[]{"C1", "As black as ________ = very dirty.", "the Ace of Spades", "ink", "midnight", "a"});
        map.add(new String[]{"C1", "I hope that you have read the contract and understand ________ it means.", "that", "how", "what", "c"});
        map.add(new String[]{"C1", "C1", "Oxfam tries to send food to countries where people are suffering ________ malnutrition.", "for", "by", "from", "c"});
        map.add(new String[]{"C1", "She's an excellent manager. She runs a really tight ________.", "deal", "ship", "bargain", "b"});
        map.add(new String[]{"C1", "His cheek was very ________ after the wasp stung him.", "wide", "swollen", "thick", "b"});
        map.add(new String[]{"C1", "What a lovely dress ________ on.", "have you", "you've got", "have you got", "b"});
        map.add(new String[]{"C1", "The ________ listened attentively to every word the vicar said in his sermon.", "sightseers", "congregation", "spectators", "b"});
        map.add(new String[]{"C1", "I doubt ________ the company will make any profit at all this year.", "when", "whether", "since", "b"});
        map.add(new String[]{"C1", " ________ down the High Street, Peter bumped into Jenny.", "On walk", "As walking", "Walking", "c"});
        map.add(new String[]{"C1", " I had run out of money but luckily I was able to ________ enough to get home.", "lend", "loan", "borrow", "c"});
        map.add(new String[]{"C1", "Never ________, we'll see the film next week.", "to mind", "you mind", "mind you", "b"});
        map.add(new String[]{"C1", "We haven't ________ thought of going abroad for a holiday because my husband is afraid of flying.", "ever", "never", "yet", "a"});
        map.add(new String[]{"C1", "The pilot said that with one engine of the plane out of action, it had been ________ over the Channel.", "touch and go", "start and return", "touch and break", "a"});
        map.add(new String[]{"C1", "C1", "All the travel arrangements ________ before we received a letter from him.", "were being made", "had been made", "have been made", "b"});
        map.add(new String[]{"C1", "He shouldn't be allowed to play in the club. He's not a ________.", "belong", "member", "partner", "b"});
        map.add(new String[]{"C1", " Going to the pub too often can easily become a bad ________.", "use", "tradition", "habit", "c"});
        map.add(new String[]{"C1", "Jenny and her sister are so ________, they could almost be twins.", "likeness", "aike", "same", "b"});
        map.add(new String[]{"C1", "The plate was right on the ________ of the table and could have been knocked off at any moment.", "border", "tip", "edge", "c"});
        map.add(new String[]{"C1", "Are you interested ________ opera?", "on", "in", "with", "b"});
        map.add(new String[]{"C1", "The opening ________ of the play took place in an army camp.", "stage", "scene", "sight", "b"});
        map.add(new String[]{"C1", "The new factory chimney was ________ than all the trees around it.", "longer", "greater", "taller", "c"});
        map.add(new String[]{"C1", " At four o'clock Mr Hutchinson still had some ________ to do in the garden.", "work", "job", "effort", "a"});
        map.add(new String[]{"C1", "When I was in the hotel my money ________.", "was stolen", "is stolen", "was stelling", "a"});
        map.add(new String[]{"C2", "The decision is yours, but I'd prefer you not ________ home until you're older.", "leave", "to leave", "leaving", "b"});
        map.add(new String[]{"C2", "This hotel has ________ and you'll have to try your luck at some other hotels.", "no rooms", "not rooms", "no space", "a"});
        map.add(new String[]{"C2", "Our neighbours ________ their hedge cut once a year.", "make", "do", "have", "c"});
        map.add(new String[]{"C2", "We'd better hurry. There's a ________ to Uncle Timothy's patience.", "top", "bottom", "limit", "c"});
        map.add(new String[]{"C2", "There are ________ trains running today because of the go-slow", "fewer", "less", "little", "a"});
        map.add(new String[]{"C2", "Some people think it is ________ to use long and little-known words.", "clever", "intentional", "skilled", "a"});
        map.add(new String[]{"C2", "We can only give you the ________ number of refugees crossing the border at the moment.", "suggestive", "nebulous", "approximate", "c"});
        map.add(new String[]{"C2", "This house is cheap, but that one is ________.", "cheaper even", "cheaper yet", "cheaper still", "c"});
        map.add(new String[]{"C2", "He's the perfect person to take on this difficult job. He's a really hard- ________ person and won't stand for any nonsense.", "ship", "nosed", "handshake", "b"});
        map.add(new String[]{"C2", "I have to be careful which soap I use, because my skin is very ________.", "sensible", "senseless", "sensitive", "c"});
        map.add(new String[]{"C2", "Heavy snowfalls have ________ all trains.", "cancelled", "postponed", "delayed", "c"});
        map.add(new String[]{"C2", "One of the main advantages ________ the new PCX 232 personal computer is that it is very simple to operate.", "for", "by", "of", "c"});
        map.add(new String[]{"C2", " I ________ seeing John tomorrow so I will give him your message.", "shall be", "may be", " would be", "a"});
        map.add(new String[]{"C2", "Whenever you go to the sales, you ________ your money buying things that nobody wants", "lose", "miss", "waste", "c"});
        map.add(new String[]{"C2", " Her plane takes ________ at 3 p.m.", "out", "on", "off", "c"});
        map.add(new String[]{"C2", "The film ________ several scenes that might upset young children.", "admits", "involves", "contains", "c"});
        map.add(new String[]{"C2", "You think that's a good idea? I'm sorry, but I don't ________ you at all.", "agree to", "agree with", "disagree to", "b"});
        map.add(new String[]{"C2", "The colours in some of the photos we took two years ago have begun to ________.", "fail", "faint", "fade", "c"});
        map.add(new String[]{"C2", "At a dinner to mark his retirement, Neville was ________ with a silver clock.", "provided", "given", "presented", "c"});
        map.add(new String[]{"C2", "You have to be rich to send a child to a private school because the fees ________.", "astrological", "aeronautical", "astronomical", "c"});
        map.add(new String[]{"C2", "There is no reason to ________ his honesty; he is absolutely sincere.", "search", "doubt", "inquire", "b"});
        map.add(new String[]{"C2", "I'm afraid the lift is out of ________ so we'll have to walk up the stairs.", "function", "movement", "order", "c"});
        map.add(new String[]{"C2", "He congratulated me ________ having got engaged.", "of", "about", "on", "c"});
        map.add(new String[]{"C2", "The waiter's tip is included ________ the bill.", "on", "in", "at", "b"});
        map.add(new String[]{"C2", "The bank will ________ you the money if you are prepared to pay them eight per cent interest on it.", "borrow", "lend", "make", "b"});
        map.add(new String[]{"C2", "Although this wine is quite cheap, it is very ________.", "drunk", "drank", "drinkable", "c"});
        map.add(new String[]{"C2", " Inflation and its upward ________ is the scourge of our days.", "trend", "bend", "stream", "a"});
        map.add(new String[]{"C2", "Little Angela hasn't ________ her shyness yet.", "got over", "got away from", "got through", "a"});
        map.add(new String[]{"C2", "- Deborah: When are they going to buy that house? - Claudia: Didn't you know? They finally decided ________.", "not to be", "not to", "not", "b"});
        map.add(new String[]{"C2", "- Do I have to make that French course? - No, you ________.", "haven't", "mustn't", "needn't", "c"});
//        Log.e("Size", String.valueOf(map.size()));
        for (String[] key : map) {
            ContentValues contentValues = new ContentValues();
            String[] arr = new String[]{"level", "question", "optiona", "optionb", "optionc", "correct"};
            for (int j = 0; j < arr.length && j < key.length; j++) {
                contentValues.put(arr[j], key[j]);
            }
            long result = myDb.insert("tests", null, contentValues);
            if (result == -1) continue;
            Log.e("hello", contentValues.toString());
        }
    }

    @SuppressLint("Range")
    public List<QuestionModel> getAllQuestionLevel(String level) {
        List<QuestionModel> ans = new ArrayList<>();
        SQLiteDatabase myDb = this.getReadableDatabase();
        String query = "select * from tests where level = " + level;
        Log.e("Query", query);
        Cursor cursor = myDb.rawQuery(query, null);
        cursor.moveToFirst();
        Log.e("OK", "ok");
        while (!cursor.isAfterLast()) {
            Log.e("OK1", "ok");
            String question = cursor.getString(cursor.getColumnIndex("question"));
            String optiona = cursor.getString(cursor.getColumnIndex("optiona"));
            String optionb = cursor.getString(cursor.getColumnIndex("optionb"));
            String optionc = cursor.getString(cursor.getColumnIndex("optionc"));
            String correct = cursor.getString(cursor.getColumnIndex("correct"));
            ans.add(new QuestionModel(question, optiona, optionb, optionc, correct));
            cursor.moveToNext();
        }

        return ans;
    }
    public String getTestResult(String username){
        SQLiteDatabase myDb = this.getReadableDatabase();
        String query = "select * from testresults where username = " + username;
        Log.e("Query", query);
        Cursor cursor = myDb.rawQuery(query, null);
        cursor.moveToFirst();
        String ans = "";
        while (!cursor.isAfterLast()) {
            Log.e("OK1", "ok");
            @SuppressLint("Range") String level = cursor.getString(cursor.getColumnIndex("level"));
            @SuppressLint("Range") double result = Double.parseDouble(cursor.getString(cursor.getColumnIndex("result")));
            ans += "Bài test: " + level + ", kết quả: " + result + '\n';
            cursor.moveToNext();
        }
        return ans;
    }
}