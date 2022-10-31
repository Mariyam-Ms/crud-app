package com.project1.crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class AdapterSg extends RecyclerView.Adapter<AdapterSg.MyViewHolder> {
    private ShowActivity showActivity;
    private List<Data> mList;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    public AdapterSg (ShowActivity showActivity,List<Data> mList){
        this.showActivity=showActivity;
        this.mList=mList;

    }

    public void deleteData(int position){
        Data item =mList.get(position);
        db.collection("Data").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyDataRemoved(position);
                            Toast.makeText(showActivity, "Deleted !!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(showActivity, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void notifyDataRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        showActivity.showData();
    }
    public void updateData(int position){
        Data item =mList.get(position);
        Bundle bundle=new Bundle();
        bundle.putString("uId",item.getId());
        bundle.putString("uTitle",item.getTitle());
        bundle.putString("uNotes",item.getNotes());
        Intent intent=new Intent(showActivity,MainActivity.class);
        intent.putExtras(bundle);
        showActivity.startActivity(intent);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_each,parent,false);
        return new MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(mList.get(position).getTitle());
        holder.notes.setText(mList.get(position).getNotes());
       holder.constraintLayout.setBackground(null);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateData(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               //bottomSheetDailog();

                return false;
            }



        });



//            if ((position % 2) == 0){
          //     holder.itemView.setBackgroundResource(R.color.blue_700);
//                mList.get(position);
////                notifyItemRemoved(position);
////            } else if (position  == 1 ){
////                holder.itemView.setBackgroundResource(R.color.grey);
////                mList.get(position);
////                notifyItemRemoved(position);
//          //  }//else (position == 0){
//               // holder.itemView.setBackgroundResource(R.color.blue_200);
//                //mList.get(position);
////                notifyItemRemoved(position);
//            } else {
//                holder.itemView.setBackgroundResource(R.color.light_100);
//                mList.get(position);
////                notifyItemRemoved(position);
//
//        }

    }







    @Override
    public int getItemCount() {
        return mList.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,notes;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.staggeredview_title);
            notes = itemView.findViewById(R.id.staggeredview_notes);
            constraintLayout=itemView.findViewById(R.id.constraintLayoudId);
        }
    }
//    public interface AdapterClickListeners{
//        void bottomSheetDailog();
//    }

}
