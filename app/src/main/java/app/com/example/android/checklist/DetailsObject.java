package app.com.example.android.checklist;

/**
 * Created by ShowMe on 7/18/16.
 */
public class DetailsObject {
    String mDetail;
    String mDescription;

    public DetailsObject(String mDetail, String mDescription) {
        this.mDetail = mDetail;
        this.mDescription = mDescription;
    }

    public String getmDetail() {
        return mDetail;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
