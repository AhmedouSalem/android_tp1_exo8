package fr.umontpellier.tp_1_8;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.List;

import fr.umontpellier.tp_1_8.data.DataStatic;
import fr.umontpellier.tp_1_8.entities.Train;
import fr.umontpellier.tp_1_8.shared.CustomCardWay;

public class AllTripsActivity extends AppCompatActivity {
    private LinearLayout tripsContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltrips);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back);
        if (upArrow != null) {
            upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        }
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        tripsContainer = findViewById(R.id.tripsContainer);
        displayAllTrips();
    }

    private void displayAllTrips() {
        List<Train> trains = DataStatic.getTrains(DataStatic.getCitiesFromAPI());

        if (trains.size() != 0) {
            for (Train train : trains) {
                TableLayout tableLayout = CustomCardWay.createItineraryTable(this, train);
                tripsContainer.addView(tableLayout);
            }
        } else {
            TextView searchCriteria = new TextView(this);
            searchCriteria.setText(getString(R.string.notripsforthisroute));
            tripsContainer.addView(searchCriteria);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
