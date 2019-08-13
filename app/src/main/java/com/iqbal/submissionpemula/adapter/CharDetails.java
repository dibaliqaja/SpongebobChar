package com.iqbal.submissionpemula.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iqbal.submissionpemula.R;

public class CharDetails extends AppCompatActivity {
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_DESCRIPTION = "extra_description";

    ImageView imgItemDetail;
    TextView tvItemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_char);

        imgItemDetail = findViewById(R.id.img_item_photo);
        tvItemDescription = findViewById(R.id.tv_item_desc);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);

        getSupportActionBar().setTitle(name);
        tvItemDescription.setText(description);

        String photo = getIntent().getStringExtra(EXTRA_PHOTO);
        Glide.with(this).load(photo).into(imgItemDetail);
    }
}
