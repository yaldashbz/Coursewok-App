package edu.sharif.courseworkapp.ui.course;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.sharif.courseworkapp.databinding.ActivityProfessorCoursePageBinding;
import edu.sharif.courseworkapp.model.user.Professor;
import edu.sharif.courseworkapp.model.user.User;

public class ProfessorCoursePage extends UserCoursePage {
    private ActivityProfessorCoursePageBinding binding;

    private void setBinding() {
        binding = ActivityProfessorCoursePageBinding.inflate(getLayoutInflater());
    }

    private void handleFab() {
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> goToCreateNewHomework());
    }

    private void handleToolbar() {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collToolbar = binding.toolbarLayout;

        Professor professor = User.getProfessorByUsername(getUsername());
        collToolbar.setTitle(professor.getDisplayName());
        collToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collToolbar.setExpandedTitleColor(Color.WHITE);
    }

    private void handleRecyclerView() {
        RecyclerView courseRecyclerView = binding.idRecyclerViewHomeworkList;
        addDivider(courseRecyclerView);
        homeworkListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(homeworkListAdapter);
        populate();
    }

    private void goToCreateNewHomework() {
        Intent intent = new Intent(ProfessorCoursePage.this, CreateNewHomeworkActivity.class);
        intent.putExtra("courseId", getCourseId());
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        handleFab();
        handleToolbar();
        handleRecyclerView();
    }
}
