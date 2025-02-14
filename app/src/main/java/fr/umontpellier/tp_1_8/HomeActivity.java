package fr.umontpellier.tp_1_8;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import fr.umontpellier.tp_1_8.data.DataStatic;
import fr.umontpellier.tp_1_8.entities.Train;
import fr.umontpellier.tp_1_8.shared.CustomCardWay;
import fr.umontpellier.tp_1_8.utils.ManageFromToEntryAutocomplete;


public class HomeActivity extends Activity {
    Button buttonRoundTrip, buttonOneWay;
    TextInputLayout returnDateLayout;
    TextInputEditText returnDate, departDate;
    LinearLayout upcomingTripsContainer;
    TextView seeMoreTextView;
    ImageView iconChangeOrderDepartArrival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonRoundTrip = findViewById(R.id.buttonRoundTrip);
        buttonOneWay = findViewById(R.id.buttonOneWay);
        returnDateLayout = findViewById(R.id.returnDateLayout);
        returnDate = findViewById(R.id.returnDate);
        departDate = findViewById(R.id.departDate);

        returnDateLayout.setVisibility(View.VISIBLE);

        buttonOneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonActive(buttonOneWay, buttonRoundTrip, returnDateLayout);
            }
        });

        buttonRoundTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonActive(buttonRoundTrip, buttonOneWay, returnDateLayout);
            }
        });

        seeMoreTextView = findViewById(R.id.handleAllWay);
        seeMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AllTripsActivity.class);
                startActivity(intent);
            }
        });



        AutoCompleteTextView editTextDeparture = findViewById(R.id.editTextDeparture);
        AutoCompleteTextView editTextArrival = findViewById( R.id.editTextArrival);

        ImageView iconChangeOrderDepartArrival = findViewById(R.id.changeOrderDepartArrival);
        iconChangeOrderDepartArrival.setClickable(true);
        iconChangeOrderDepartArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = editTextDeparture.getText().toString();
                editTextDeparture.setText(editTextArrival.getText());
                editTextArrival.setText(temp);
            }
        });

        ManageFromToEntryAutocomplete.manageFieldAutoCompleteFromAndTo(this, editTextDeparture, editTextArrival);
        manageDateEntry ();

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String departureCity = editTextDeparture.getText().toString().trim();
                String arrivalCity = editTextArrival.getText().toString().trim();
                String departureDate = departDate.getText().toString().trim();

                if (departureCity.isEmpty()) {
                    editTextDeparture.setError(getString(R.string.ifempty_from));
                    return;
                }

                if (arrivalCity.isEmpty()) {
                    editTextArrival.setError(getString(R.string.ifempty_to));
                    return;
                }

                if (departureDate.isEmpty()) {
                    departDate.setError(getString(R.string.ifempty_departdate));
                    return;
                }

                if (arrivalCity.toLowerCase().equals(departureCity.toLowerCase())) {
                    editTextArrival.setError(getString(R.string.ifdepartcityeqarrivalcity));
                    return;
                }

                Intent intent = new Intent(HomeActivity.this, SearchResultsActivity.class);

                intent.putExtra("departureCity", departureCity);
                intent.putExtra("arrivalCity", arrivalCity);
                intent.putExtra("departureDate", departureDate);

                startActivity(intent);
            }
        });

        upcomingTripsContainer = findViewById(R.id.upcomingTripsContainer);
        displayUpcomingTrips();
    }

    private void displayUpcomingTrips() {
        List<Train> trains = DataStatic.getTrains(DataStatic.getCitiesFromAPI());
        int limitIndex = 0;
        for (Train train : trains) {
            limitIndex ++;
            if (limitIndex <= 4) {
                TableLayout tableLayout = CustomCardWay.createItineraryTable(this, train);
                upcomingTripsContainer.addView(tableLayout);
            }
        }
    }





    private void manageDateEntry () {
        TextInputEditText departDate = findViewById(R.id.departDate);
        TextInputEditText returnDate = findViewById(R.id.returnDate);


        departDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(departDate);
            }
        });

        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(returnDate);
            }
        });

    }
    private String getMonthName(int month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[month];
    }

    private void showDatePickerDialog(final TextInputEditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                HomeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = dayOfMonth + " " + getMonthName(monthOfYear) + " " + year;
                        editText.setText(date);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void setButtonActive(Button activeButton, Button inactiveButton, LinearLayout returnDateLayout) {
        activeButton.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.primary));
        activeButton.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white));
        Drawable drawableActive = activeButton.getCompoundDrawables()[0];
        if (drawableActive != null) {
            drawableActive.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.white), PorterDuff.Mode.SRC_IN);
        }

        inactiveButton.setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.secondColor));
        inactiveButton.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
        Drawable drawableInactive = inactiveButton.getCompoundDrawables()[0];
        if (drawableInactive != null) {
            drawableInactive.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.black), PorterDuff.Mode.SRC_IN);
        }

        if (activeButton == buttonRoundTrip) {
            returnDateLayout.setVisibility(View.VISIBLE);
        } else {
            returnDateLayout.setVisibility(View.GONE);
            returnDate.setText("");
        }
    }

}
