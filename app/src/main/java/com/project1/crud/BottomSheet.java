package com.project1.crud;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {
    private AdapterSg adapter;
    public BottomSheet (AdapterSg adapter){
        super();
        this.adapter=adapter;
    }
    BottomSheetListner bottomSheetListner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v =inflater.inflate(R.layout.bottomsheet_dailog,container,false);
        TextView aDelete=v.findViewById(R.id.delete);
        TextView hShare=v.findViewById(R.id.share);
        aDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // adapter.deleteData(position);
               // bottomSheetListner.onClick(adapter.deleteData(position));
            }
        });
    return v;
    }
    public interface BottomSheetListner{
        void onClick();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListner=(BottomSheetListner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context+"Interface must be implemented");
        }
    }
}
