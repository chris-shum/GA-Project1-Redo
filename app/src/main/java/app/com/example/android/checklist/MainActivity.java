package app.com.example.android.checklist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
    }

    @Override
    protected void onResume() {
        mRecyclerViewAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
