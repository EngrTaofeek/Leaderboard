package com.taofeek.leaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Button submit;
    ImageButton back_button;
    EditText mFirstName,mLastName,mEmail,mGithub;
    private SubmitInterface mSubmitInterface;
    private static Retrofit retrofit;
    private static final String baseURl = "https://docs.google.com/forms/d/e/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        submit = findViewById(R.id.submit_button_act);
        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email_address);
        mGithub = findViewById(R.id.github_link);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showConfirmationDialog();
                String first_name = getEditText(mFirstName);
                String last_name = getEditText(mLastName);
                String email = getEditText(mEmail);
                String github = getEditText(mGithub);
                if (!(first_name.isEmpty()) && !(last_name.isEmpty()) && !(email.isEmpty()) && !(github.isEmpty())){
                    showConfirmationDialog();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                    LayoutInflater layoutInflater = SubmitActivity.this.getLayoutInflater();
                    View v = layoutInflater.inflate(R.layout.confirmation_dialog, null);
                    builder.setView(v);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    ImageView cancelImage =  v.findViewById(R.id.cancel_image);
                    cancelImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });


                    Button yesButton =  v.findViewById(R.id.button_yes);
                    /*yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                postDetails(first_name,last_name,email,github);
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    });*/
                }
                else {
                    Snackbar.make(findViewById(R.id.submit_parent), "Please fill all fields correctly", Snackbar.LENGTH_LONG).show();
                }

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
          }
    public String getEditText(EditText editText){
        String text = editText.getText().toString().trim();
        return text;
    }
    private void postDetails(String firstName, String lastName, String email, String github) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSubmitInterface = retrofit.create(SubmitInterface.class);

        Call<Void> stringCall = mSubmitInterface.postProjectData(firstName, lastName, email, github);
        stringCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                LayoutInflater layoutInflater = SubmitActivity.this.getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.success_dialog, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                LayoutInflater layoutInflater = SubmitActivity.this.getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.failure_dialog, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }


}