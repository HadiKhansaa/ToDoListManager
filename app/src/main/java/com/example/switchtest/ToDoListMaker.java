package com.example.switchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ToDoListMaker extends AppCompatActivity {
    EditText note;
    Button addButton, cancelButton;
    Intent intent;
    MyDatabaseHelper MyDb;
    boolean edit = false;
    String oldNote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_maker);

        edit = getIntent().getBooleanExtra("edit", true);
        oldNote = getIntent().getStringExtra("note");

        MyDb = new MyDatabaseHelper(this);

        note = findViewById(R.id.editNote);
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        intent = new Intent(this, ToDoLists.class);

        note.setText(oldNote);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (note.getText().toString().equals("")) {
                        Toast.makeText(ToDoListMaker.this, "Please Enter a note.", Toast.LENGTH_SHORT).show();
                    } else {
                        MyDb.addNote(note.getText().toString(), edit, oldNote);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(ToDoListMaker.this, "Please Enter a valid note.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}