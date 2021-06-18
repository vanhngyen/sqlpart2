package com.example.sqlpart2.activity.activity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlpart2.R;
import com.example.sqlpart2.activity.database.DBHepler;

public class ListUserAct extends AppCompatActivity {
    private DBHepler db;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        db = new DBHepler(this);
        ListView  lvUser = (ListView)findViewById(R.id.lvUser);
        c = db.getAllUser();
        adapter = new SimpleCursorAdapter(this, R.layout.item_user,c,new String[]{
            DBHepler.ID,DBHepler.NAME,DBHepler.GENDER}, new  int[]{R.id.tvId,R.id.tvName,R.id.tvGender},
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvUser.setAdapter(adapter);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor =(Cursor) adapter.getItem(position);
                int _id = cursor.getInt(cursor.getColumnIndex(DBHepler.ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHepler.NAME));
                String gender = cursor.getString(cursor.getColumnIndex(DBHepler.GENDER));
                String des = cursor.getString(cursor.getColumnIndex(DBHepler.DES));

                Intent intent = new Intent(ListUserAct.this, UpdateAct.class);
                intent.putExtra(DBHepler
.ID,_id);
                intent.putExtra(DBHepler
.NAME,name);
                intent.putExtra(DBHepler
.GENDER,gender);
                intent.putExtra(DBHepler
.DES,des);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        c = db.getAllUser();
        adapter.changeCursor(c);
        adapter.notifyDataSetChanged();
        db.close();
    }
}
