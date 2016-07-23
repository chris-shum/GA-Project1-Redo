package app.com.example.android.checklist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ShowMe on 7/20/16.
 */
public class DialogBox {

    public void CreateDialogBox(final Boolean main, Context context, final int position, final RecyclerViewAdapter recyclerViewAdapter) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        if (main) {
            dialogBuilder.setView(R.layout.dialog_box_main);
            dialogBuilder.setTitle("Add List Title");
        } else {
            dialogBuilder.setView(R.layout.dialog_box_details);
            dialogBuilder.setTitle("Add Item Details");
        }
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Singleton singleton = Singleton.getInstance();

                Dialog dialog = (Dialog) dialogInterface;
                if (main) {
                    EditText mEditTextTitle = (EditText) dialog.findViewById(R.id.textTitle);
                    String mEditTextTitleString = mEditTextTitle.getText().toString();

                    if (mEditTextTitleString.isEmpty()) {
                        Toast.makeText(((Dialog) dialogInterface).getContext(), "Womp", Toast.LENGTH_SHORT).show();
                    } else {
                        singleton.getMainObjectArrayList().add(new MainObject(mEditTextTitleString, new ArrayList<DetailsObject>()));
                    }

                } else {
                    EditText mEditTextItem = (EditText) dialog.findViewById(R.id.textItem);
                    EditText mEditTextDesciption = (EditText) dialog.findViewById(R.id.textDescription);
                    String mEditTextItemString = mEditTextItem.getText().toString();
                    String mEditTextDescriptionString = mEditTextDesciption.getText().toString();

                  if (mEditTextItemString.isEmpty()) {
                        Toast.makeText(((Dialog) dialogInterface).getContext(), "Womp", Toast.LENGTH_SHORT).show();
                    } else {
                        singleton.getMainObjectArrayList().get(position).getmDetailsObjectArrayList().add(new DetailsObject(mEditTextItemString, mEditTextDescriptionString));
                    }
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        final AlertDialog bundler = dialogBuilder.create();

        bundler.show();
    }
}
