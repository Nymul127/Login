package com.example.practice;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



/*
public class MainActivity extends AppCompatActivity {
DatabaseHelper databaseHelper;// object create of DatabaseHelper class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        // Get writable database
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}*/
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelper databaseHelper; // Object for DatabaseHelper class
    private Button button,signUpbutton;
    private EditText editTextU;
    private EditText editTextP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//LogIn Button
        button=(Button)findViewById(R.id.button) ;
        signUpbutton=(Button)findViewById(R.id.buttonsi) ;

        // Password Edit Text
        editTextP=(EditText) findViewById(R.id.editTextTextPassword) ;
        // User Id edit text
        editTextU=(EditText) findViewById(R.id.edit_text_id) ;

        button.setOnClickListener(this);
        signUpbutton.setOnClickListener(this);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Get writable database
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // Set padding for system bars (ensure 'main' ID exists in layout XML)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //OnClick Listener()
    @Override
    public void onClick(View v) {
        String username = editTextU.getText().toString().trim();//
        String password = editTextP.getText().toString().trim();

            if (v.getId() == R.id.button) {
                // Action for button with ID 'button'

                Boolean result=databaseHelper.findPassword(username,password);
                if(result==true)
                {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username and password didn't match ", Toast.LENGTH_SHORT).show();
                }

            } else if (v.getId() == R.id.buttonsi) {
                // Navigate to signup activity
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        }



}
