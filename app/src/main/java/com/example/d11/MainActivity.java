package com.example.d11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements Preview.OnFragmentInteractionListener,Team.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout);
        final TextView textView=(TextView)findViewById(R.id.textView2);
        ImageView imageView=(ImageView)findViewById(R.id.logo);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        tabLayout.addTab(tabLayout.newTab().setText("PreView"));
        tabLayout.addTab(tabLayout.newTab().setText("Team"));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Matches.class);
                startActivity(intent);
            }
        });
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("TeamsOnly").child("GameName");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                textView.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
