package onlineshopping.buyon.com.buyon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    String product_image,name;
    ImageView product_imageview;
    TextView product_name;
    GridView grid_stores;

    String[] stores={ "Amazon",
            "Flipkart",
            "Snapdeal",
            "Shopclues",
            "Aliexpress",
            "Ajio",
            "Jabong",
            "Myntra"
    };
    int[] imageId_stores = {
            R.drawable.amazonlogo,
            R.drawable.flipkartlogo,
            R.drawable.snapdealogo,
            R.drawable.shopclueslogo,
            R.drawable.aliexpress,
            R.drawable.ajio,
            R.drawable.jabonglogo,
            R.drawable.myntralogo




    };

    Random rand=new Random();
    int r1=rand.nextInt(70);
    int r2=rand.nextInt(70);
    int r3=rand.nextInt(70);
    int r4=rand.nextInt(70);
    int r5=rand.nextInt(70);
    int r6=rand.nextInt(70);
    int r7=rand.nextInt(70);
    int r8=rand.nextInt(70);




    int[] discount_tag={r1,r2,r3,r4,r5,r6,r7,r8};
    ListView store_list;
    List<DataModelList> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView off_=findViewById(R.id.off_discount);

        int max_discount=discount_tag[0];
        for (int i=1;i<discount_tag.length;i++){
            if(discount_tag[i]>max_discount){
                max_discount=discount_tag[i];
            }
        }



        Integer size=max_discount;
        off_.setText(size.toString()+" % Discount");


        lists=new ArrayList<>();
        store_list=findViewById(R.id.list_stores);
        final Bundle extras = getIntent().getExtras();

        lists.add(new DataModelList(R.drawable.amazonlogo,discount_tag[0],""));
        lists.add(new DataModelList(R.drawable.flipkartlogo,discount_tag[1],""));
        lists.add(new DataModelList(R.drawable.shopclueslogo,discount_tag[2],""));
        lists.add(new DataModelList(R.drawable.snapdealogo,discount_tag[3],""));
        lists.add(new DataModelList(R.drawable.aliexpresslogo,discount_tag[4],""));
        lists.add(new DataModelList(R.drawable.jabonglogo,discount_tag[5],""));
        lists.add(new DataModelList(R.drawable.myntralogo,discount_tag[6],""));
        lists.add(new DataModelList(R.drawable.ajio,discount_tag[7],""));


        ListAdapter adapter=new ListAdapter(this,R.layout.list_row,lists);
        store_list.setAdapter(adapter);



        product_imageview=findViewById(R.id.product_image);
        product_name=findViewById(R.id.product_name);

        Bundle b=this.getIntent().getExtras();
        int image=b.getInt("image");
        product_imageview.setImageResource(image);




        product_name.setText(extras.getString("name"));

        store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("amazon_s"));
                        url_pass.putExtra("redirect_url",extras.getString("amazon_p"));
                        url_pass.putExtra("redirect_url",extras.getString("amazon_j"));
                        url_pass.putExtra("redirect_url",extras.getString("amazon_fs"));
                        startActivity(url_pass);
                        break;

                    case 1:
                         url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("flipkart_s"));
                        url_pass.putExtra("redirect_url",extras.getString("flipkart_p"));
                        url_pass.putExtra("redirect_url",extras.getString("flipkart_j"));
                        url_pass.putExtra("redirect_url",extras.getString("flipkart_fs"));
                        startActivity(url_pass);
                        break;

                    case 2:
                        url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("shopclues_s"));
                        url_pass.putExtra("redirect_url",extras.getString("shopclues_p"));
                        url_pass.putExtra("redirect_url",extras.getString("shopclues_j"));
                        startActivity(url_pass);
                        break;
                    case 3:
                        url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("snapdeal_s"));
                        url_pass.putExtra("redirect_url",extras.getString("snapdeal_p"));


                        startActivity(url_pass);
                        break;
                    case 4:
                        url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("aliexpress_s"));
                        url_pass.putExtra("redirect_url",extras.getString("aliexpress_p"));
                        startActivity(url_pass);
                        break;

                    case 5:
                        url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("jabong_s"));
                        url_pass.putExtra("redirect_url",extras.getString("jabong_p"));
                        startActivity(url_pass);
                        break;

                    case 6:
                        url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("myntra_s"));
                        url_pass.putExtra("redirect_url",extras.getString("myntra_p"));
                        startActivity(url_pass);
                        break;


                    case 7:
                        url_pass=new Intent(DetailActivity.this,WebActivity.class);
                        url_pass.putExtra("redirect_url",extras.getString("ajio_s"));
                        url_pass.putExtra("redirect_url",extras.getString("ajio_p"));
                        startActivity(url_pass);
                        break;


                    default:
                        Toast.makeText(DetailActivity.this, "hey", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });





    }
}
