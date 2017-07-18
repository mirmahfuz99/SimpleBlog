package com.mirmahfuz.simpleblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BlogSingleActivity extends AppCompatActivity {

    private String mPost_key = null;
    private DatabaseReference mDatabase;
    private ImageView mBlogSingleImage;
    private TextView mBlogSingleTitle;
    private TextView mBlogSingleDes;
    private TextView mUserName;
    private FirebaseAuth mAuth;
    private Button msingleRemovebtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_single);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mAuth = FirebaseAuth.getInstance();
        mPost_key = getIntent().getExtras().getString("blog_id");
        mBlogSingleDes = (TextView)findViewById(R.id.single_post_des);
        mBlogSingleImage = (ImageView) findViewById(R.id.single_post_image);
        mBlogSingleTitle = (TextView) findViewById(R.id.single_post_title);
        mUserName = (TextView)findViewById(R.id.single_post_username);
        msingleRemovebtn = (Button) findViewById(R.id.singleremove_btn);




        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_title = (String)dataSnapshot.child("title").getValue();
                String post_des = (String) dataSnapshot.child("des").getValue();
                String post_image = (String)dataSnapshot.child("image").getValue();
                String post_uid = (String)dataSnapshot.child("uid").getValue();
                String user_name = (String)dataSnapshot.child("username").getValue();


                mBlogSingleTitle.setText(post_title);
                mBlogSingleDes.setText(post_des);
                mUserName.setText(user_name);
                Picasso.with(BlogSingleActivity.this).load(post_image).into(mBlogSingleImage);


                if (mAuth.getCurrentUser().getUid().equals(post_uid)) {
                    msingleRemovebtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        msingleRemovebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mDatabase.child(mPost_key).removeValue();
                Intent mainIntent = new Intent(BlogSingleActivity.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });
    }
}
