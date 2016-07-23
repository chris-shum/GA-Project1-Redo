package app.com.example.android.checklist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ShowMe on 7/18/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    int position;
    ArrayList<MainObject> mainObjectArrayList;
    MainObject mainObject;
    Boolean main;
    RecyclerViewAdapter recyclerViewAdapter = this;

    public RecyclerViewAdapter(ArrayList<MainObject> mainObjectArrayList, Boolean main) {
        this.mainObjectArrayList = mainObjectArrayList;
        this.main = main;
    }

    public RecyclerViewAdapter(MainObject mainObject, Boolean main, int position) {
        this.mainObject = mainObject;
        this.main = main;
        this.position = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        if (main) {
            holder.textView.setText(mainObjectArrayList.get(position).getmTitle());
        } else {
            String combo = mainObject.getmDetailsObjectArrayList().get(position).getmDetail()+"\n     -"+mainObject.getmDetailsObjectArrayList().get(position).getmDescription();
            holder.textView.setText(combo);
        }

    }

    @Override
    public int getItemCount() {
        if (main) {
            if (mainObjectArrayList.size() != 0) {
                return mainObjectArrayList.size();
            } else {
                return 0;
            }
        } else {
            if (mainObject.getmDetailsObjectArrayList().size() != 0) {
                return mainObject.getmDetailsObjectArrayList().size();
            } else {
                return 0;
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(main){
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Position", getAdapterPosition());
                context.startActivity(intent);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            Singleton singleton = Singleton.getInstance();
            DialogBoxEdit dialogBoxEdit = new DialogBoxEdit();
            dialogBoxEdit.EditDialogBox(main, view.getContext(), getAdapterPosition(), singleton.getMainObjectArrayList().get(position), recyclerViewAdapter);
            return true;
        }
    }
}
