package app.com.example.android.checklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogBox dialogBox = new DialogBox();
                dialogBox.CreateDialogBox(main, DetailsActivity.this, intent.getIntExtra("Position",0), mRecyclerViewAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        mRecyclerViewAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
