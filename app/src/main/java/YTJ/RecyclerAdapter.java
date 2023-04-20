package YTJ;

import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_application.MainActivity;
import com.example.my_application.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Item> dataSet;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewId;

        TextView textViewDate;

        TextView textViewForm;

        ImageView imageViewIcon;

        LinearLayout visible;
        LinearLayout hidden;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewId);
            this.textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            this.textViewForm = (TextView) itemView.findViewById(R.id.textViewForm);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);

            this.visible= (LinearLayout) itemView.findViewById(R.id.visible);
            this.hidden= (LinearLayout) itemView.findViewById(R.id.hidden);

            visible.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hidden.getVisibility() == view.VISIBLE) {
                        TransitionManager.beginDelayedTransition(hidden, new AutoTransition());
                        hidden.setVisibility(View.GONE);
                    } else {
                        TransitionManager.beginDelayedTransition(hidden, new AutoTransition());
                        hidden.setVisibility(View.VISIBLE);
                    }
                }
            }
            );
        }
    }



    public RecyclerAdapter(ArrayList<Item> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewId = holder.textViewId;
        TextView textViewDate = holder.textViewDate;
        TextView textViewForm = holder.textViewForm;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewId.setText("Company ID: " + dataSet.get(listPosition).getBusinessId());
        textViewDate.setText("Registration Date: " + dataSet.get(listPosition).getRegistrationDate());
        textViewForm.setText("Company Form: " + dataSet.get(listPosition).getCompanyForm());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}


