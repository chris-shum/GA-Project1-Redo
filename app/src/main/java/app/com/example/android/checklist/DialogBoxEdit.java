package app.com.example.android.checklist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

/**
 * Created by ShowMe on 7/20/16.
 */
public class DialogBoxEdit {
    Singleton singleton;

    // TODO: 7/20/16 edit info to make things changeable


    public void EditDialogBox(final Boolean main, Context context, final int position, final MainObject mainObject) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        singleton = Singleton.getInstance();
        if (main) {
            dialogBuilder.setView(R.layout.dialog_box_main);
            dialogBuilder.setTitle("Edit List Title");
        } else {
            dialogBuilder.setView(R.layout.dialog_box_details);
            dialogBuilder.setTitle("Edit Item Details");
        }
        dialogBuilder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Dialog dialog = (Dialog) dialogInterface;
                if (main) {
                    EditText mEditTextTitle = (EditText) dialog.findViewById(R.id.textTitle);
                    String mEditTextTitleString = mEditTextTitle.getText().toString();

                    if (mEditTextTitleString.isEmpty()) {
                        mEditTextTitle.setError("Can't leave empty");
                    } else {
                        singleton.getMainObjectArrayList().get(position).setmTitle(mEditTextTitleString);
                    }

                } else {
                    EditText mEditTextItem = (EditText) dialog.findViewById(R.id.textItem);
                    EditText mEditTextDesciption = (EditText) dialog.findViewById(R.id.textDescription);
                    String mEditTextItemString = mEditTextItem.getText().toString();
                    String mEditTextDescriptionString = mEditTextDesciption.getText().toString();

                    if (mEditTextItemString.isEmpty()) {
                        mEditTextItem.setError("Can't leave empty");
                    } else {
                        mainObject.getmDetailsObjectArrayList().get(position).setmDetail(mEditTextItemString);
                        mainObject.getmDetailsObjectArrayList().get(position).setmDescription(mEditTextDescriptionString);
                    }

                }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(main){
                    singleton.getMainObjectArrayList().remove(position);
                }else{
                    mainObject.getmDetailsObjectArrayList().remove(position);
                }
            }
        });

        final AlertDialog builder = dialogBuilder.create();
        builder.show();
        if (main) {
            EditText editTextTitle = (EditText) builder.findViewById(R.id.textTitle);
            editTextTitle.setText(singleton.getMainObjectArrayList().get(position).getmTitle());
        } else {
            EditText editTextItem = (EditText) builder.findViewById(R.id.textItem);
            EditText editTextDescription = (EditText) builder.findViewById(R.id.textDescription);

            editTextItem.setText(mainObject.getmDetailsObjectArrayList().get(position).getmDetail());
            editTextDescription.setText(mainObject.getmDetailsObjectArrayList().get(position).getmDescription());
        }
    }
}
