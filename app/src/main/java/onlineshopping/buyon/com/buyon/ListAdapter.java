package onlineshopping.buyon.com.buyon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<DataModelList> {
    private final Context context;
    private final int resource;
    private  final List<DataModelList> heroList;


    public ListAdapter(Context context, int resource, List<DataModelList> heroList) {
        super(context, resource, heroList);
        this.context=context;
        this.resource=resource;
        this.heroList=heroList;



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView store_image = view.findViewById(R.id.storename);
        TextView discount = view.findViewById(R.id.discounttag);

        //getting the hero of the specified position
        DataModelList hero = heroList.get(position);

        //adding values to the list item
        store_image.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        //discount.setText(hero.getDiscount());
        Integer d=hero.getDiscount();
        discount.setText(d.toString()+"% Discount");



        return view;
    }
}
