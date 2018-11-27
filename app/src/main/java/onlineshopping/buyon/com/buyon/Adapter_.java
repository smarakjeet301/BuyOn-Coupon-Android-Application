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

public class Adapter_ extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private Context context;
    private LayoutInflater inflater;
    List<DataModel> data= Collections.emptyList();
    DataModel current;
    int currentPos=0;



    OnItemClickListener clickListener;

    public Adapter_(Context context, List<DataModel> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view=inflater.inflate(R.layout.container_fish, parent,false);
        final MyHolder holder=new MyHolder(view);

        return holder;
    }



    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataModel current=data.get(position);
        myHolder.Name.setText(current.name);

        // load image into imageview using glide
//        Glide.with(context).load(current.image)
//                .into(myHolder.image);


        Picasso.get().load(current.image).into(myHolder.image);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView Name;
        ImageView image;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            Name=  itemView.findViewById(R.id.textFishName);
            image= itemView.findViewById(R.id.ivFish);


        }



    }

}