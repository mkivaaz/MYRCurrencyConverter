package magic.com.myrcurrencyconverter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import magic.com.myrcurrencyconvertor.R;

/**
 * Created by Muguntan on 9/16/2017.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.myViewHolder>{

    private LayoutInflater inflater;
    List<CountryList> data = new ArrayList<>();
    AdapterView.OnItemClickListener itemClickListener;
    Context context;
    String CountryName;
    String Flag;

    public CountryAdapter(Context context,List<CountryList> data) {
        inflater = LayoutInflater.from(context);
        this.data=data;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.country_adapter,null);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        CountryList Curr_Country = data.get(position);
        holder.CName.setText(Curr_Country.getCountryName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView CName;

        public myViewHolder(final View itemView) {
            super(itemView);

            CName = (TextView) itemView.findViewById(R.id.countryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CountryName=data.get(getAdapterPosition()).getCountryName();
                    Flag=data.get(getAdapterPosition()).getFlag();
                    Intent intent = new Intent(context,CountryDetails.class);
                    intent.putExtra("CountryName",CountryName );
                    intent.putExtra("Flag",Flag );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            }

        }
    }

