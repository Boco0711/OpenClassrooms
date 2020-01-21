package com.openclassrooms.entrevoisins.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourProfileDetailActivity extends AppCompatActivity {

    private NeighbourApiService neighbourApiService;
    private ImageView mNeighbourAvatar;
    private TextView mNeighbourName;
    private TextView mNeighbourNameInContact;
    private TextView mNeighbourDescription;
    private TextView mNeighbourLocation;
    private TextView mNeighbourPhoneNumber;
    private TextView mNeighbourSocialMedia;
    private FloatingActionButton mNeighbourFavButton;
    private boolean isNeighbourAFav;

    private int imageNumber;
    int[] images = {R.drawable.ic_star_white_24dp, R.drawable.ic_star_border_white_24dp};

    private int neighbourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile_detail);

        neighbourApiService = DI.getNeighbourApiService();
        mNeighbourAvatar = (ImageView) findViewById(R.id.neighbourProfilPic);
        mNeighbourName = (TextView) findViewById(R.id.neighbourNameInProfilePicture);
        mNeighbourNameInContact = (TextView) findViewById(R.id.neighbourNameInDescription);
        mNeighbourLocation = (TextView) findViewById(R.id.textLocation);
        mNeighbourPhoneNumber = (TextView) findViewById(R.id.textPhone);
        mNeighbourSocialMedia = (TextView) findViewById(R.id.textSocial);
        mNeighbourDescription = (TextView) findViewById(R.id.neighbourAPropos);
        mNeighbourFavButton = (FloatingActionButton) findViewById(R.id.isNeighbourFav);

        Bundle b = getIntent().getExtras();
        String neighbourName = null;
        String neighbourAvatar = null;
        String neighbourDescription = null;
        String neighbourAdresse = null;
        String neighbourPhone = null;
        String neighbourSocial = null;

        if(b != null) {
            neighbourId = b.getInt("neighbourId");
            neighbourName = b.getString("neighbourName");
            neighbourAvatar = b.getString("neighbourAvatar");
            neighbourDescription = b.getString("neighbourDescription");
            neighbourAdresse =b.getString("neighbourAdresse");
            neighbourPhone = b.getString("neighbourPhone");
            neighbourSocial = b.getString("neighbourSocial");
            isNeighbourAFav = b.getBoolean("neighbourIsFav");
        }
        mNeighbourName.setText(neighbourName);
        mNeighbourNameInContact.setText(neighbourName);
        mNeighbourLocation.setText(neighbourAdresse);
        mNeighbourPhoneNumber.setText(neighbourPhone);
        mNeighbourSocialMedia.setText(neighbourSocial);
        mNeighbourDescription.setText(neighbourDescription);
        mNeighbourDescription.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbourAvatar)
                .override(100, 70)
                .centerCrop()
                .into(mNeighbourAvatar);

        this.configureToolbar();

        if (isNeighbourAFav) {
            mNeighbourFavButton.setImageResource(R.drawable.ic_star_white_24dp);
            imageNumber = 0;
        } else {
            mNeighbourFavButton.setImageResource(R.drawable.ic_star_border_white_24dp);
            imageNumber = 1;
        }

        buttonClick();
    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarNeighbourDetail);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
    }

    public void buttonClick() {
        mNeighbourFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageNumber++;
                imageNumber = imageNumber % images.length;
                mNeighbourFavButton.setImageResource(images[imageNumber]);

                if (imageNumber == 0) {
                    neighbourApiService.setNeighbourFav(neighbourId, true);
                } else {
                    neighbourApiService.setNeighbourFav(neighbourId, false);
                }
            }
        });
    }
}