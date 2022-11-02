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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AdapterSg extends RecyclerView.Adapter<AdapterSg.MyViewHolder> implements View.OnLongClickListener {
    private ShowActivity showActivity;
    //Context mContext;
    View.OnLongClickListener onLongClickListener;
    private List<Data> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public AdapterSg(ShowActivity showActivity, List<Data> mList) {
        this.showActivity = showActivity;
        this.mList = mList;

    }
//    private ClickListners clickListners;
//
//    public AdapterSg(ClickListners clickListners){
//        this.clickListners= clickListners;
//    }


    public void deleteData(int position) {
        Data item = mList.get(position);
        db.collection("Data").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyDataRemoved(position);
                            Toast.makeText(showActivity, "Deleted !!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(showActivity, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void notifyDataRemoved(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        showActivity.showData();
    }

    public void updateData(int position) {
        Data item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uTitle", item.getTitle());
        bundle.putString("uNotes", item.getNotes());
        Intent intent = new Intent(showActivity, MainActivity.class);
        intent.putExtras(bundle);
        showActivity.startActivity(intent);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_each, parent, false);
        v.setOnLongClickListener(this);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int colourcode = getRandomColour();
        MyViewHolder.constraintLayout.setBackgroundColor(holder.itemView.getResources().getColor(colourcode, null));

        final Data data = mList.get(position);
        holder.title.setText(data.getTitle());
        holder.notes.setText(data.getNotes());
      //  holder.constraintLayout.setBackground(null);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(position);
            }
        });
//


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//            //  clickListners.bottomSheetDailog();
////                BottomSheet bottomSheet=new BottomSheet(adapterSg);
////                bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());
//
//                BottomSheet bottomSheet = new BottomSheet();
//                bottomSheet.show(((FragmentActivity) mContext).getSupportFragmentManager(),bottomSheet.getTag());
//
//
//                return false;
//            }
//
//
//
//        });


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


//    private void bottomSheetDailog(View v) {
//       // BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragment();
//      //  bottomSheetDialogFragment.show().getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
//
//        BottomSheetDialogFragment bottomSheetFragment = new BottomSheetDialogFragment();
//        //bottomSheetFragment.show(BottomSheet.getSupportFragmentManager(), bottomSheetFragment.getTag());
//
//   ///
//}
//final Dialog dialog = new Dialog(mContext);
    //dialog.setContentView(R.layout.BottomSheet);
    // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    //dailog.setContentView.(R.layout.BottomSheet);


//
//
//
//


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public boolean OnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        onLongClickListener.onLongClick(v);
        return true;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, notes;
        static ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.staggeredview_title);
            notes = itemView.findViewById(R.id.staggeredview_notes);
            constraintLayout = itemView.findViewById(R.id.constraintLayoudId);
        }
    }

    private int getRandomColour() {
        List<Integer> colourcode = new ArrayList<>();
        colourcode.add(R.color.beach1);
        colourcode.add(R.color.beach2);
        colourcode.add(R.color.beach3);
        colourcode.add(R.color.beach4);
        colourcode.add(R.color.beach5);
        colourcode.add(R.color.beach6);
        colourcode.add(R.color.beach7);
        colourcode.add(R.color.beach8);
        colourcode.add(R.color.beach9);
        colourcode.add(R.color.beach10);
        colourcode.add(R.color.green1);
        colourcode.add(R.color.green2);
        colourcode.add(R.color.grey);
        colourcode.add(R.color.sky1);
        colourcode.add(R.color.sky2);
        colourcode.add(R.color.sky3);
        colourcode.add(R.color.sky4);
        colourcode.add(R.color.sky5);
        colourcode.add(R.color.sky6);
        colourcode.add(R.color.sky7);
        colourcode.add(R.color.lavender);
        Random random=new Random();
        int number=random.nextInt(colourcode.size());
        return colourcode.get(number);
    }
}

//    public interface ClickListners{
//       void  bottomSheetDailog();
//    }


