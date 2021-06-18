package com.example.sqlpart2.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlpart2.R;
import com.example.sqlpart2.activity.database.DBHepler
;

public class UpdateAct  extends AppCompatActivity implements View.OnClickListener {
    private DBHepler
 db ;
    private  int _id;
    private EditText edName;
    private EditText edgender;
    private EditText edDes;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        db = new DBHepler
(this);
        initView();
        Intent intent = getIntent();
        _id = intent.getIntExtra(DBHepler
.ID,0);
        String name = intent.getStringExtra(DBHepler
.NAME);
        String gender = intent.getStringExtra(DBHepler
.GENDER);
        String des = intent.getStringExtra(DBHepler
.DES);

        edName.setText(name);
        edgender.setText(gender);
        edDes.setText(des);

    }
    private  void  initView(){
        edName = (EditText) findViewById(R.id.edName);
        edgender = (EditText) findViewById(R.id.edGender);
        edDes = (EditText) findViewById(R.id.edDes);
        Button btUpdate = (Button) findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(this);
        Button btDelete = (Button) findViewById(R.id.btDelete);
        btDelete.setOnClickListener(this);

    }
    private  void  onUpdate(){
        String isUpdate = db.updateUser(_id,edName.getText().toString(),
            edgender.getText().toString()+"update", edDes.getText().toString());
        Toast.makeText(this,isUpdate,Toast.LENGTH_SHORT).show();
        finish();
    }
    private  void onDelete(){
        Toast.makeText(this,db.deleteUser(_id),Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btUpdate:
                onUpdate();
                break;
            case  R.id.btDelete:
                onDelete();
                break;
            default:
                break;
        }
    }
}
