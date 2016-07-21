package app.com.example.android.checklist;

import java.util.ArrayList;

/**
 * Created by ShowMe on 7/18/16.
 */
public class MainObject {
    String mTitle;
    ArrayList<DetailsObject> mDetailsObjectArrayList;

    public MainObject(String mTitle, ArrayList<DetailsObject> mDetailsObjectArrayList) {
        if(mDetailsObjectArrayList == null){
            mDetailsObjectArrayList = new ArrayList<>();
        }
        this.mTitle = mTitle;
        this.mDetailsObjectArrayList = mDetailsObjectArrayList;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public ArrayList<DetailsObject> getmDetailsObjectArrayList() {
        return mDetailsObjectArrayList;
    }

    public void setmDetailsObjectArrayList(ArrayList<DetailsObject> mDetailsObjectArrayList) {
        this.mDetailsObjectArrayList = mDetailsObjectArrayList;
    }
}
