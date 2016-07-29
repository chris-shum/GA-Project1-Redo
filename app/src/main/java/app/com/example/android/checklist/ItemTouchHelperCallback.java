package app.com.example.android.checklist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by ShowMe on 7/27/16.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter mAdapter;
    RecyclerViewAdapter recyclerViewAdapter;
    Boolean main;
    Context context;
    MainObject mainObject;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter, RecyclerViewAdapter recyclerViewAdapter, Boolean main, Context context) {
        this.mAdapter = mAdapter;
        this.recyclerViewAdapter = recyclerViewAdapter;
        this.main = main;
        this.context = context;
    }

    public ItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter, RecyclerViewAdapter recyclerViewAdapter, Boolean main, Context context, MainObject mainObject) {
        this.mAdapter = mAdapter;
        this.recyclerViewAdapter = recyclerViewAdapter;
        this.main = main;
        this.context = context;
        this.mainObject = mainObject;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Singleton singleton = Singleton.getInstance();
        if (direction == 16) {
            if (main) {
                singleton.getMainObjectArrayList().remove(viewHolder.getAdapterPosition());
                recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                recyclerViewAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), singleton.getMainObjectArrayList().size());
            } else {
                mainObject.getmDetailsObjectArrayList().remove(viewHolder.getAdapterPosition());
                recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                recyclerViewAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), mainObject.getmDetailsObjectArrayList().size());
            }
        }
        if (direction == 32) {
            DialogBoxEdit dialogBoxEdit = new DialogBoxEdit();
            dialogBoxEdit.EditDialogBox(main, context, viewHolder.getAdapterPosition(), mainObject, recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


}
