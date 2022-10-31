package com.project1.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private RecyclerView recyclerView;
    private FirebaseFirestore database;
    private AdapterSg adapterSg;
    private List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        imageButton=findViewById(R.id.add_imageBTn);
        recyclerView=findViewById(R.id.recycler_view);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,MainActivity.class));

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        database=FirebaseFirestore.getInstance();
        list=new ArrayList<>();
        adapterSg=new AdapterSg(this,list);

        recyclerView.setAdapter(adapterSg);
//        ItemTouchHelper touchHelper=new ItemTouchHelper(new TouchHelper(adapterSg));
//        touchHelper.attachToRecyclerView(recyclerView);

        showData();
    }
    public void showData(){
        database.collection("Data").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){
                            Data data= new Data(snapshot.getString("id"),snapshot.getString("title"),snapshot.getString("notes"));
                            list.add(data);
                        }
                        adapterSg.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}