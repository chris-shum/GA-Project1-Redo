package app.com.example.android.checklist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Singleton mSingleton;
    GridLayoutManager mGridLayoutManager;
    RecyclerViewAdapter mRecyclerViewAdapter;
    Boolean main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main = true;

        mSingleton = Singleton.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mSingleton.getMainObjectArrayList(), main);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox dialogBox = new DialogBox();
                dialogBox.CreateDialogBox(main, MainActivity.this, 0, mRecyclerViewAdapter);
            }
        });

        ItemTouchHelper.Callback callback =
                new ItemTouchHelperCallback(mRecyclerViewAdapter, mRecyclerViewAdapter, main, this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPref = getSharedPreferences("app.com.example.android.checklist", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("Key", null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<MainObject>>() {
            }.getType();
            ArrayList<MainObject> arrayList = gson.fromJson(json, type);
            mSingleton.mainObjectArrayList.clear();
            mSingleton.mainObjectArrayList.addAll(arrayList);
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("app.com.example.android.checklist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mSingleton.getMainObjectArrayList());
        editor.putString("Key", json);
        editor.commit();
    }

    // TODO: 7/23/16 create sqlite database that holds json data of singleton.mainobjectarraylist
}
