package com.fenton.librarymanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class addbook extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;
    TextInputEditText bookid, bookname, authorname;
    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        bookid=findViewById(R.id.bookid);
        bookname=findViewById(R.id.bookname);
        authorname=findViewById(R.id.authorname);

        Button uploadimage = findViewById(R.id.uploadimage);
        Button chooseFileButton = findViewById(R.id.openfilechooser);
        addbutton=findViewById(R.id.addbutton);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });

        chooseFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }
    private void processinsert() {
        Map<String,Object> map=new HashMap<>();
        map.put("bookid",bookid.getText().toString());
        map.put("bookname",bookname.getText().toString());
        map.put("authorname",authorname.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("bookdetails").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        bookid.setText("");
                        bookname.setText("");
                        authorname.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Insert failure",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadimage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), PICK_FILE_REQUEST,null);
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle errors
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");  // You can specify the type of files you want to allow here, e.g., "image/*" for images
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), PICK_FILE_REQUEST,null);
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle errors
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {

//            TODO: Handle the selected file
        }
    }
}