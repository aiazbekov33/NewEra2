package com.example.newera.ui.boarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newera.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class BoardAdapter extends FragmentPagerAdapter {

    public BoardAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new OnBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.FRAGMENT_POSITION,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
