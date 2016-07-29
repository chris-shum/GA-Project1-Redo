package app.com.example.android.checklist;

/**
 * Created by ShowMe on 7/27/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
