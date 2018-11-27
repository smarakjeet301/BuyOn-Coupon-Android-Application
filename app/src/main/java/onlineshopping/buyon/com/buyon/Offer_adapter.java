package onlineshopping.buyon.com.buyon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Offer_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private Context context;
    private LayoutInflater inflater;
    List<Offer_Data_model> data= Collections.emptyList();
    Offer_Data_model current;
    int currentPos=0;



    OnItemClickListener clickListener;

    public Offer_adapter(Context context, List<Offer_Data_model> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view=inflater.inflate(R.layout.offers_view, parent,false);
        final MyHolder holder=new MyHolder(view);

        return holder;
    }



    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Offer_Data_model current=data.get(position);
        myHolder.Name.setText(current.offer_store_name);

            myHolder.store_offer_category.setText(current.offer_category);


        myHolder.offer_tagwords.setText(current.offer_tagwords);


        Picasso.get().load(current.offer_store_image).into(myHolder.store_offer_image);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView Name,store_offer_category,offer_tagwords;
        ImageView store_offer_image;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            Name=  itemView.findViewById(R.id.offer_store_name);
            store_offer_image= itemView.findViewById(R.id.offer_store_image);
            store_offer_category=itemView.findViewById(R.id.offer_store_category);
            offer_tagwords=itemView.findViewById(R.id.offer_store_tagwords);



        }



    }

}