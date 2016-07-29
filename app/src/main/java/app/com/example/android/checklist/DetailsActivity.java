package app.com.example.android.checklist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Singleton mSingleton;
    LinearLayoutManager mLinearLayoutManager;
    RecyclerViewAdapter mRecyclerViewAdapter;
    Boolean main;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main = false;

        mSingleton = Singleton.getInstance();
        intent = getIntent();

        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, main);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mSingleton.getMainObjectArrayList().get(intent.getIntExtra("Position", 999)), false, intent.getIntExtra("Position",999));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        ItemTouchHelper.Callback callback =
        new ItemTouchHelperCallback(mRecyclerViewAdapter, mRecyclerViewAdapter, main, this, mSingleton.getMainObjectArrayList().get(intent.getIntExtra("Position", 0)));
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogBox dialogBox = new DialogBox();
                dialogBox.CreateDialogBox(main, DetailsActivity.this, intent.getIntExtra("Position",0), mRecyclerViewAdapter);
            }
        });
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
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
}
