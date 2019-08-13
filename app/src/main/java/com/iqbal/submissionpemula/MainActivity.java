package com.iqbal.submissionpemula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.iqbal.submissionpemula.adapter.CardCharAdapter;
import com.iqbal.submissionpemula.adapter.CharDetails;
import com.iqbal.submissionpemula.adapter.GridCharAdapter;
import com.iqbal.submissionpemula.adapter.ListCharAdapter;
import com.iqbal.submissionpemula.model.Char;
import com.iqbal.submissionpemula.model.CharData;

import java.util.ArrayList;

import static com.iqbal.submissionpemula.adapter.CharDetails.EXTRA_DESCRIPTION;
import static com.iqbal.submissionpemula.adapter.CharDetails.EXTRA_NAME;
import static com.iqbal.submissionpemula.adapter.CharDetails.EXTRA_PHOTO;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvChar;
    private ArrayList<Char> list = new ArrayList<>();
    private String title = "List View";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvChar = findViewById(R.id.rv_char);
        rvChar.setHasFixedSize(true);

        list.addAll(CharData.getListData());
        showRecyclerlist();
    }

    private void showRecyclerlist() {
        rvChar.setLayoutManager(new LinearLayoutManager(this));
        ListCharAdapter listCharAdapter = new ListCharAdapter(list);
        rvChar.setAdapter(listCharAdapter);

        listCharAdapter.setOnItemClickCallback(new ListCharAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Char ch) {
                showSelectedChar(ch);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerGrid() {
        rvChar.setLayoutManager(new GridLayoutManager(this, 2));
        GridCharAdapter gridCharAdapter = new GridCharAdapter(list);
        rvChar.setAdapter(gridCharAdapter);

        gridCharAdapter.setOnItemClickCallback(new GridCharAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Char ch) {
                showSelectedChar(ch);
            }
        });
    }

    private void showRecyclerCard() {
        rvChar.setLayoutManager(new LinearLayoutManager(this));
        CardCharAdapter cardCharAdapter = new CardCharAdapter(list);
        rvChar.setAdapter(cardCharAdapter);

        cardCharAdapter.setOnItemClickCallback(new CardCharAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Char ch) {
                showSelectedChar(ch);
            }
        });
    }

    private void showSelectedChar(Char ch) {
        Intent charDetail = new Intent(MainActivity.this, CharDetails.class);
        charDetail.putExtra(EXTRA_NAME, ch.getName());
        charDetail.putExtra(EXTRA_PHOTO, ch.getPhoto());
        charDetail.putExtra(EXTRA_DESCRIPTION, ch.getDesc());
        startActivity(charDetail);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "List View";
                setActionBarTitle(title);
                showRecyclerlist();
                break;

            case R.id.action_grid:
                title = "Grid View";
                setActionBarTitle(title);
                showRecyclerGrid();
                break;

            case R.id.action_card:
                title = "Card View";
                setActionBarTitle(title);
                showRecyclerCard();
                break;

            case R.id.action_about:
                Intent aboutIntent = new Intent(MainActivity.this, About.class);
                startActivity(aboutIntent);
                break;
        }
    }
}
