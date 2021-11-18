package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginui.adapters.OrdersAdapter;
import com.example.loginui.api.RequestPlaceholder;
import com.example.loginui.api.RetrofitBuilder;
import com.example.loginui.pojos.Orders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

//    public TextView user_id;
    public RecyclerView ordersRecyclerView;
    public List<Orders> ordersList;
    public OrdersAdapter ordersAdapter;

    private SwipeRefreshLayout swipeRefresh;

    public RetrofitBuilder retrofitBuilder;
    public RequestPlaceholder requestPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        retrofitBuilder = new RetrofitBuilder();
        requestPlaceholder = retrofitBuilder.getRetrofit().create(RequestPlaceholder.class);
        swipeRefresh = findViewById(R.id.swipeRefresh);



        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        ordersList = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(ordersList, this);
        ordersRecyclerView.setAdapter(ordersAdapter);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateOrders();
            }
        });

        populateOrders();
    }

    public void populateOrders() {
        try {
            ordersList.clear();

            Call<List<Orders>> ordersCall = requestPlaceholder.getAllOrders("PFrzbojadGSyfqu6xFxjJ3woRjPZhG95LaWyc9FJesN1W2dRG82lObz1gVKae71M", "1");

            ordersCall.enqueue(new Callback<List<Orders>>() {
                @Override
                public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            ordersList.addAll(response.body());

                            ordersAdapter.notifyDataSetChanged();

                            swipeRefresh.setRefreshing(false);

                        } else {
                            Log.e("ERR_GET_ORDERS", response.message() + "");
                            Toast.makeText(HomeActivity.this, "Error getting orders", Toast.LENGTH_SHORT).show();
                        }
                    } else  {
                        Log.e("ERR_GET_ORDERS", response.message() + "");
                        Toast.makeText(HomeActivity.this, "Error getting orders", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<Orders>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Log.e("ERR_GET_ORDERS", e.getMessage());
        }


    }
}