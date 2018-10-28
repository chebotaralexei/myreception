package com.electricsheeps.myreception;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.electricsheeps.myreception.presentation.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, LoginFragment.newInstance())
                    .commit();
        }

    }
}
