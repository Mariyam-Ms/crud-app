package com.project1.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText notes,title;
    private Button  mShow ,save;
    private FirebaseFirestore database;
    private String uId,uTitle,uNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes=findViewById(R.id.editNotes);
        title=findViewById(R.id.editTitle);
        mShow=findViewById(R.id.button_show);
        save =findViewById(R.id.button_save);
        database=FirebaseFirestore.getInstance();
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
            save.setText("Update");
            uTitle=bundle.getString("uTitle");
            uNotes=bundle.getString("uNotes");
            uId=bundle.getString("uId");
            title.setText(uTitle);
            notes.setText(uNotes);


        }else{
            save.setText("Save");
        }
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String mTitle = title.getText().toString();
               String mNotes = notes.getText().toString();
               Bundle bundle1 = getIntent().getExtras();
               if(bundle1 != null){
               String id = uId;
               updateToFireStore(id, mTitle,mNotes);


           }else{
               String id= UUID.randomUUID().toString();
               saveFireStore(id,mTitle,mNotes);}

           }
       });
       mShow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,ShowActivity.class));
               finish();
           }
       });
    }

    private void updateToFireStore(String id,String mTitle,String mNotes){

        database.collection("Data").document(id).update("title",mTitle,"notes",mNotes)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Task uploaded Sucessfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Error : "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }
    private void saveFireStore(String id,String mTitle ,String mNotes){
        if(!mTitle.isEmpty() && !mNotes.isEmpty()){
            HashMap<String,Object>  map = new HashMap<>();
            map.put("id", id);
            map.put("title", mTitle);
            map.put("notes", mNotes);

            database.collection("Data").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this, "Task Added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        }else{
            Toast.makeText(this, "Empty Stuff Not allowed", Toast.LENGTH_SHORT).show();
        }
    }
}