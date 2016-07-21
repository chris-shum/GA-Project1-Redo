package app.com.example.android.checklist;

import java.util.ArrayList;

/**
 * Created by ShowMe on 7/18/16.
 */
public class Singleton {
    ArrayList<MainObject> mainObjectArrayList;

    public Singleton(ArrayList<MainObject> mainObjectArrayList) {
        if(mainObjectArrayList ==null){
            mainObjectArrayList = new ArrayList<>();
        }
        this.mainObjectArrayList = mainObjectArrayList;
    }

    public ArrayList<MainObject> getMainObjectArrayList() {
        if(mainObjectArrayList == null){
            mainObjectArrayList = new ArrayList<>();
        }
        return mainObjectArrayList;
    }

    public void setMainObjectArrayList(ArrayList<MainObject> mainObjectArrayList) {
        this.mainObjectArrayList = mainObjectArrayList;
    }

    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }
}
