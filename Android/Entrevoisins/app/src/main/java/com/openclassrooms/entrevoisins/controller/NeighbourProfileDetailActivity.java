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

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourProfileDetailActivity extends AppCompatActivity {

    private NeighbourApiService neighbourApiService;
    @BindView(R.id.neighbourProfilPic)
    ImageView mNeighbourAvatar;

    @BindView(R.id.neighbourNameInProfilePicture)
    TextView mNeighbourName;

    @BindView(R.id.neighbourNameInDescription)
    TextView mNeighbourNameInContact;

    @BindView(R.id.neighbourAPropos)
    TextView mNeighbourDescription;

    @BindView(R.id.textLocation)
    TextView mNeighbourLocation;

    @BindView(R.id.textPhone)
    TextView mNeighbourPhoneNumber;

    @BindView(R.id.textSocial)
    TextView mNeighbourSocialMedia;

    @BindView(R.id.isNeighbourFav)
    FloatingActionButton mNeighbourFavButton;

    private boolean isNeighbourAFav;

    private int imageNumber;
    int[] images = {R.drawable.ic_star_white_24dp, R.drawable.ic_star_border_white_24dp};

    private int neighbourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile_detail);
        ButterKnife.bind(this);

        neighbourApiService = DI.getNeighbourApiService();

        Bundle b = getIntent().getExtras();
        String neighbourAvatar = null;

        //Mise a net de l'image mise dans les faux profil. A retirer par la suite.
        if(b != null) {
            neighbourId = b.getInt("neighbourId");
            neighbourAvatar = b.getString("neighbourAvatar");
            mNeighbourName.setText(b.getString("neighbourName"));
            mNeighbourNameInContact.setText(b.getString("neighbourName"));
            mNeighbourLocation.setText(b.getString("neighbourAdresse"));
            mNeighbourPhoneNumber.setText(b.getString("neighbourPhone"));
            mNeighbourSocialMedia.setText(b.getString("neighbourSocial"));
            mNeighbourDescription.setText(b.getString("neighbourDescription"));
            isNeighbourAFav = b.getBoolean("neighbourIsFav");
        }
        String neighbourAvatarInHd = neighbourAvatar.substring(0, neighbourAvatar.indexOf("150")) + "500" + neighbourAvatar.substring(neighbourAvatar.indexOf("?"));

        mNeighbourDescription.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbourAvatarInHd)
                .into(mNeighbourAvatar);

        this.configureToolbar();

        /**
         * Set image depending on fav Status of neighbour
         */
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

    /**
     * Change the image of mNeighbourFavButton depending on favorite status
     * Set or unset favorite depending on fav status before the click.
     */
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