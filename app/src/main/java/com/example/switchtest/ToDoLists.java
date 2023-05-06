package com.example.switchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoLists extends AppCompatActivity {
    ListView ToDoList;
    Button addToDo;
    Intent intent;
    ArrayList<String> ToDoArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_lists);

        ToDoList = findViewById(R.id.toDoList);
        intent = new Intent(this, ToDoListMaker.class);
        addToDo = findViewById(R.id.addToDo);
        ToDoArray = new ArrayList<String>();
        fillList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ToDoArray);
        ToDoList.setAdapter(adapter);
        intent.putExtra("note", "");
        intent.putExtra("edit", false);

        ToDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = parent.getItemAtPosition(position).toString();
                intent.putExtra("note", note);
                intent.putExtra("edit", true);
                startActivity(intent);
            }
        });

        ToDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyDatabaseHelper MyDb = new MyDatabaseHelper(ToDoLists.this);
                MyDb.deleteNote(parent.getItemAtPosition(position).toString());
                //startActivity(getIntent());
                ToDoArray.remove(position);
                adapter.notifyDataSetChanged();

                return false;
            }
        });

        addToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void fillList(){
        try{
            MyDatabaseHelper MyDb = new MyDatabaseHelper(this);
            Cursor c = MyDb.readToDoListTableData();
            c.moveToFirst();
            do{
                String note =  c.getString(1);
                ToDoArray.add(note);
            }while(c.moveToNext());
        }catch (Exception e){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}