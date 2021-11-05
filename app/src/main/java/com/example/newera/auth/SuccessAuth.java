package com.example.newera.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newera.MainActivity;
import com.example.newera.R;
import com.example.newera.databinding.ActivitySuccessAuthBinding;


public class SuccessAuth extends AppCompatActivity {
    private ActivitySuccessAuthBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuccessAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpListener();
    }

    private void setUpListener() {
        binding.getIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessAuth.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

}