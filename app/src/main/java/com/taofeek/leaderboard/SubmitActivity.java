package com.taofeek.leaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SubmitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Button submit;
    ImageButton back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        submit = findViewById(R.id.submit_button_act);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });
        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmitActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showConfirmationDialog() {
        DialogFragment newFragment = new ConfirmationFragment();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }


}