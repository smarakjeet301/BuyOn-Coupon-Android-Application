package onlineshopping.buyon.com.buyon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVFishPrice;
    private Adapter_ mAdapter;
    ViewPager viewPager;
    int RecyclerViewItemPosition ;
    final List<DataModel> data=new ArrayList<>();
    ProgressBar progressBar;
       GridView grid,grid_stores;
    String[] web = {
            "Fashion",
            "Tickets",
            "Food",
            "Sports",
            "Toys",
            "Electronics",
            "Books",
            "Health",
            "Recharge",
            "Travel",
            "Grocery",
            "Furniture"


    } ;

    String[] stores={ "Amazon",
            "Flipkart",
            "Snapdeal",
            "Shopclues",
            "Aliexpress",
            "Ajio",
            "Jabong",
            "Myntra"
    };


    int[] imageId = {
            R.drawable.ic_shoesfashion,
            R.drawable.ic_movietickets,
            R.drawable.ic_coffeefood,
            R.drawable.ic_sportsswim,
            R.drawable.ic_toys,
            R.drawable.ic_television,
            R.drawable.ic_notebook,
            R.drawable.ic_health,
            R.drawable.ic_recharge,
            R.drawable.ic_travel,
            R.drawable.ic_grocery,
            R.drawable.ic_furniture,



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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
        grid=findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                   @Override
                   public void onItemClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                       switch (position){
                           case 2:
                               Intent i=new Intent(MainActivity.this,Food.class);
                               startActivity(i);
                               break;

                           case 3:
                               i=new Intent(MainActivity.this,Offer.class);
                               startActivity(i);
                               break;
                           case 9:
                               startActivity(new Intent(MainActivity.this,Travel.class));
                               break;

                           case 8:
                               startActivity(new Intent(MainActivity.this,Recharge.class));
                               break;

                           case 6:
                               startActivity(new Intent(MainActivity.this,Books.class));
                               break;

                           case 10:
                               startActivity(new Intent(MainActivity.this,Grocery.class));
                               break;
                           case 7:
                               startActivity(new Intent(MainActivity.this,Health.class));
                               break;
                           case 0:
                               startActivity(new Intent(MainActivity.this,FashionActivity.class));
                               break;

           }

            }
        });

        viewPager =findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

