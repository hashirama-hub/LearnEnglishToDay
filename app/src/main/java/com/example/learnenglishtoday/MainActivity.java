package com.example.learnenglishtoday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewpager;
    DBHelper db;
    DBTestHelper dbTest;
    DBReportHelper dbReport;
    FloatingActionButton btnDanhsach;
    DrawerLayout mDrawer;
    String user;
    ExpandableListView mExpandableListView;
    ExpandableListAdapter mExpandableAdapter;

    List<String> list_group;
    HashMap<String, List<String>> list_children;
    CircleImageView profile_image;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private int lastExpandablePosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_image = findViewById(R.id.profile_image);
        db = new DBHelper(this);
        dbTest = new DBTestHelper(this);
        dbReport = new DBReportHelper(this);
        typcast_my_object();
        String mHeadings[]= getResources().getStringArray(R.array.headings);
        String mDescriptions[]= getResources().getStringArray(R.array.descriptions);
        Bundle extras = getIntent().getExtras();
        if (extras == null) user = "";
        else user = extras.getString("email");
        CustomSlider  mCustomerslider = new CustomSlider(this, mHeadings, mDescriptions);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, profile_image);
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());
                if (user == ""){
                    popup.getMenu().add("Đăng nhập");
                }else{
                    popup.getMenu().add("Lịch sử bài test");
                    popup.getMenu().add("Đăng xuất");
                    popup.getMenu().add("Xóa tài khoản");
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle() == "Đăng xuất"){
                            popup.getMenu().clear();
                            popup.getMenu().add("Đăng nhập");
                            user = "";
                            Intent intent = new Intent(MainActivity.this, LoginForm.class);
                            startActivity(intent);
                        }else if (item.getTitle() == "Đăng nhập"){
                            Intent intent = new Intent(MainActivity.this, LoginForm.class);
                            startActivity(intent);
                        }else if (item.getTitle() == "Lịch sử bài test"){
                            ArrayList<String> test_report = dbReport.getReports(user);
                            String ans = "";
                            for (String i: test_report) ans += i;
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                            builder1.setMessage(ans);
                            builder1.setCancelable(true);
                            builder1.setPositiveButton(
                                    "DONE",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }else if (item.getTitle() == "Xóa tài khoản"){
                            db.deleteAccount(MainActivity.this, user);
                            popup.getMenu().clear();
                            popup.getMenu().add("Đăng nhập");
                            user = "";
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        mViewpager.setAdapter(mCustomerslider);
        btnDanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.isDrawerOpen(Gravity.LEFT)) {
                    mDrawer.closeDrawer(Gravity.LEFT);
                }else{
                    mDrawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        mExpandableAdapter = new CustomExpandableListViewAdapter(this, list_group, list_children);
        mExpandableListView.setAdapter(mExpandableAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if(groupPosition==11){
                    Intent intent = new Intent(MainActivity.this, GrammarTestActivity.class);
                    intent.putExtra("child_id", childPosition);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }else if(groupPosition!=11){
                    Intent intent = new Intent(MainActivity.this, PdfViewer.class);
                    intent.putExtra("child_id", childPosition);
                    intent.putExtra("group_id", groupPosition);
                    startActivity(intent);
                }
                return true;
            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandablePosition!=-1 && groupPosition!= lastExpandablePosition){
                    mExpandableListView.collapseGroup(lastExpandablePosition);
                }
                lastExpandablePosition=groupPosition;
            }
        });
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, R.string.drawer_open, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, R.string.drawer_close,Toast.LENGTH_SHORT).show();
            }
        };
        mDrawer.setDrawerListener(mActionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void typcast_my_object() {
        mViewpager=findViewById(R.id.viewPager);
        btnDanhsach=findViewById(R.id.btn_danhsach);
        mDrawer=findViewById(R.id.mDrawer);
        mExpandableListView = findViewById(R.id.expandable_listview);

        list_children= ExpandableListData.getData();
        list_group= new ArrayList<String>(list_children.keySet());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
            return super.onOptionsItemSelected(item);
        }
    }

