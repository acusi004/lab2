package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lab2.Adapter.ToDo_Adapter;
import com.example.lab2.dao.ToDoDao;
import com.example.lab2.database.DbHelper;
import com.example.lab2.model.ToDoModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_title, edt_content, edt_date, edt_type;
    Button btn_add;
    ImageView iv_xoa, iv_edit;

    ArrayList<ToDoModel> list = new ArrayList<>();

    RecyclerView recyclerView;
    ToDo_Adapter toDo_adapter;
    Context context= this;
    public void mipMap(){
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_Content);
        edt_date = findViewById(R.id.edt_date);
        edt_type = findViewById(R.id.edt_type);
        btn_add = findViewById(R.id.btn_add);
        iv_edit = findViewById(R.id.iv_edit);
        iv_xoa = findViewById(R.id.iv_delete);
        recyclerView = findViewById(R.id.rcv_toDoList);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mipMap();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ToDoDao toDoDao1 = new ToDoDao(this);
        ArrayList<ToDoModel> list = toDoDao1.getAll();
        toDo_adapter = new ToDo_Adapter(this, list, toDoDao1);
        recyclerView.setAdapter(toDo_adapter);
    }
}