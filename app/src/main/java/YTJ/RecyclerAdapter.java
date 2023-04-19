package YTJ;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_application.MainActivity;
import com.example.my_application.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Item> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewId;

        TextView textViewDate;

        TextView textViewForm;

        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewId = (TextView) itemView.findViewById(R.id.textViewId);
            this.textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            this.textViewForm = (TextView) itemView.findViewById(R.id.textViewForm);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
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


