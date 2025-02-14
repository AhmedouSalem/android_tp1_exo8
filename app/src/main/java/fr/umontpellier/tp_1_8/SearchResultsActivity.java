package fr.umontpellier.tp_1_8;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.umontpellier.tp_1_8.data.DataStatic;
import fr.umontpellier.tp_1_8.entities.Train;
import fr.umontpellier.tp_1_8.shared.CustomCardWay;


public class SearchResultsActivity extends AppCompatActivity {
    private LinearLayout resultsContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back);
        if (upArrow != null) {
            upArrow.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        }
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        String departureCity = getIntent().getStringExtra("departureCity");
        String arrivalCity = getIntent().getStringExtra("arrivalCity");
        String departureDate = getIntent().getStringExtra("departureDate");

        resultsContainer = findViewById(R.id.resultsContainer);

        filterAndDisplayTrains(departureCity, arrivalCity, departureDate);
    }

    private void filterAndDisplayTrains(String departureCity, String arrivalCity, String departureDate) {
        List<Train> trains = DataStatic.getTrains(DataStatic.getCitiesFromAPI());

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        try {
            Date inputDate = sdf.parse(departureDate);
            int counter = 0;
            for (Train train : trains) {
                Date trainDate = sdf.parse(train.getTrip().getDate());
                if (trainDate.compareTo(inputDate) >= 0 && train.getTrip().getDepartureCity().getName().toLowerCase().equals(departureCity.toLowerCase()) && train.getTrip().getArrivalCity().getName().toLowerCase().equals(arrivalCity.toLowerCase())) {
                    TableLayout tableLayout = CustomCardWay.createItineraryTable(this, train);
                    resultsContainer.addView(tableLayout);
                    counter ++;
                }
            }
            if (counter == 0) {
                TextView searchCriteria = new TextView(this);
                searchCriteria.setText(getString(R.string.notripsforthisroute));
                resultsContainer.addView(searchCriteria);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
