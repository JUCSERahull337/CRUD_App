package com.example.crud_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,contact,dob;
    Button update,view,delete,insert;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        dob=findViewById(R.id.dob);


        insert = findViewById(R.id.btnInsert);
        update=findViewById(R.id.btnUpdate);
        view=findViewById(R.id.btnView);
        delete=findViewById(R.id.btnDelete);

        DB = new DBHelper(this);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata= DB.insertuserdata(nameTXT,contactTXT,dobTXT);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();

                }
            }
        });



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata= DB.updateuserdata(nameTXT,contactTXT,dobTXT);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT= name.getText().toString();

                Boolean checkdeletedata= DB.deletedata(nameTXT);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this,"Last data Deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();

                }
            }
        });




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this , "No entry exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : " + res.getString(0)+"\n");
                    buffer.append("Contact : " + res.getString(1)+"\n");
                    buffer.append("Date of Birth : " + res.getString(2)+"\n");


                }
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }
}