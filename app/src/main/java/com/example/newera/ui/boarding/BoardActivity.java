package com.example.newera.ui.boarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newera.auth.SigningActivity;
import com.example.newera.databinding.ActivityBoardBinding;
import com.example.newera.utils.Constants;

public class BoardActivity extends AppCompatActivity {

    private ActivityBoardBinding binding;
    boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkShow();
        initViewPager();


    }

    private void checkShow() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.IS_SHOW_FILE, MODE_PRIVATE);
        isShow= sharedPreferences.getBoolean(Constants.IS_SHOW,false);
        if (isShow) {
            startActivity(new Intent(this, SigningActivity.class));
        }
    }

    private void initViewPager() {
        binding.viewPagerBoard.setAdapter(new BoardAdapter(getSupportFragmentManager()));
    }
}