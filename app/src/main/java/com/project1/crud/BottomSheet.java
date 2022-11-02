package com.project1.crud;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {
//    private AdapterSg adapter;
//    private int position;
//    public BottomSheet (AdapterSg adapter){
//        super();
//        this.adapter=adapter;
//    }
//    BottomSheetListner bottomSheetListner;
//
//    public BottomSheet() {
//        super();
//        this.adapter=adapter;
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//    View v =inflater.inflate(R.layout.bottomsheet_dailog,container,false);
//        TextView aDelete=v.findViewById(R.id.delete);
//        TextView hShare=v.findViewById(R.id.share);
//        aDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             // bottomSheetDailog(String position);
//             //  bottomSheetListner.onClick()
//              // bottomSheetListner.onClick();
//               adapter.deleteData(position);
//               dismiss();
//
//            }
//        });
//        hShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "You Can Share", Toast.LENGTH_SHORT).show();
//            }
//        });
//    return v;
//    }
//    public interface BottomSheetListner{
//        void onClick();
//
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            bottomSheetListner=(BottomSheetListner) context;
//        }catch (ClassCastException e){
//            throw new ClassCastException(context+"Interface must be implemented");
//        }
//    }
}
