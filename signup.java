package com.example.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signup extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private EditText editTextN;
    private EditText editTextE;
    private EditText editTextU;
    private EditText editTextP;
    userdetails userdetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        button = (Button) findViewById(R.id.buttons);
        editTextN = (EditText) findViewById(R.id.editTextText2);
        editTextE = (EditText) findViewById(R.id.editTextText3);
        editTextU = (EditText) findViewById(R.id.editTextTextPassword);
        editTextP = (EditText) findViewById(R.id.editTextTextPassword2);

        databaseHelper = new DatabaseHelper(this);

        button.setOnClickListener(this);
        userdetails = new userdetails();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v) {
        String name = editTextN.getText().toString().trim();//
        String email = editTextE.getText().toString().trim();
        String username = editTextU.getText().toString().trim();//
        String password = editTextP.getText().toString().trim();

        userdetails.setName(name);
        userdetails.setEmail(email);
        userdetails.setUsername(username);
        userdetails.setPassword(password);
        long rowId = databaseHelper.insertdata(userdetails);


        if (rowId > 0) {
            Toast.makeText(getApplicationContext(), "Row" + rowId + "is successfully inserted.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Row insertion is failed", Toast.LENGTH_SHORT).show();
        }
    }
}