package com.openclassrooms.entrevoisins.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;

public class NeighbourProfileDetailActivity extends AppCompatActivity {

    private ImageView mNeighbourAvatar;
    private TextView mNeighbourName;
    private TextView mNeighbourNameInContact;
    private TextView mNeighbourDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile_detail);

        mNeighbourAvatar = (ImageView) findViewById(R.id.neighbourProfilPic);
        mNeighbourName = (TextView) findViewById(R.id.neighbourNameInProfilePicture);
        mNeighbourNameInContact = (TextView) findViewById(R.id.neighbourNameInDescription);
        mNeighbourDescription = (TextView) findViewById(R.id.neighbourAPropos);

        Bundle b = getIntent().getExtras();
        String neighbourName = null;
        String neighbourAvatar = null;
        String neighbourDescription = null;
        if(b != null) {
            neighbourName = b.getString("neighbourName");
            neighbourAvatar = b.getString("neighbourAvatar");
            neighbourDescription = b.getString("neighbourDescription");
        }
        mNeighbourName.setText(neighbourName);
        mNeighbourNameInContact.setText(neighbourName);
        mNeighbourDescription.setText(neighbourDescription);

        //CropTransformation cropTransformation = new CropTransformation(100, 90, CropType.TOP);

        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbourAvatar)
                .override(100, 70)
                //.bitmapTransform(new CropTransformation(context,300,100,CropTransformation.CropType.TOP))
                .centerCrop()
                .into(mNeighbourAvatar);

        this.configureToolbar();
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarNeighbourDetail);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
    }
}