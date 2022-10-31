package com.project1.crud;

//
//public class TouchHelper extends ItemTouchHelper.SimpleCallback {
//  private  AdapterSg adapter;
//    public TouchHelper(AdapterSg adapter) {
//        super(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
//        this.adapter=adapter;
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//      final int position = viewHolder.getAdapterPosition();
//      if (direction == ItemTouchHelper.LEFT) {
//        adapter.updateData(position);
//        adapter.notifyDataSetChanged();
//      } else {
//      adapter.deleteData(position);
//      }
//
//    }
//
//  @Override
//  public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
//                          @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
//                          int actionState, boolean isCurrentlyActive) {
////      new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
////
////              .create()
////              .decorate();
//      super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//  }
//}

