package fr.umontpellier.tp_1_8.shared;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import fr.umontpellier.tp_1_8.R;
import fr.umontpellier.tp_1_8.entities.Train;

public class CustomCardWay {
    public static TableLayout createItineraryTable(Context context, Train train) {
        TableLayout tableLayout = new TableLayout(context);
        LinearLayout.LayoutParams tableLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tableLayout.setLayoutParams(tableLayoutParams);
        tableLayout.setPadding(20, 20, 20, 20);
        tableLayout.setBackgroundResource(R.drawable.linearlayout_background_10dp);
        tableLayout.setColumnShrinkable(0, true);
        tableLayout.setColumnShrinkable(2, true);
        tableLayout.setColumnStretchable(1, true);
        tableLayoutParams.setMargins(0, 16, 0, 16);

        TableRow row1 = new TableRow(context);
        TextView stpText = new TextView(context);
        stpText.setText("STP");
        stpText.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));

        TextView dateText = new TextView(context);
        dateText.setText(train.getTrip().getDate());
        dateText.setGravity(Gravity.CENTER);
        dateText.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));

        TextView xpgText = new TextView(context);
        xpgText.setText("XPG");
        xpgText.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));

        row1.addView(stpText);
        row1.addView(dateText);
        row1.addView(xpgText);

        tableLayout.addView(row1);

        TableRow row2 = new TableRow(context);
        TextView departureCity = new TextView(context);
        departureCity.setTypeface(departureCity.getTypeface(), Typeface.BOLD_ITALIC);
        departureCity.setTextSize(18);
        departureCity.setText(train.getTrip().getDepartureCity().getName());

        LinearLayout iconLayout = new LinearLayout(context);
        iconLayout.setOrientation(LinearLayout.HORIZONTAL);
        iconLayout.setGravity(Gravity.CENTER);

        ImageView departureIcon = new ImageView(context);
        departureIcon.setImageResource(R.drawable.train_depart);

        ImageView travelIcon = new ImageView(context);
        travelIcon.setImageResource(R.drawable.trajet);

        iconLayout.addView(departureIcon);
        iconLayout.addView(travelIcon);

        TextView arrivalCity = new TextView(context);
        arrivalCity.setTypeface(arrivalCity.getTypeface(), Typeface.BOLD_ITALIC);
        arrivalCity.setTextSize(18);
        arrivalCity.setText(train.getTrip().getArrivalCity().getName());

        row2.addView(departureCity);
        row2.addView(iconLayout);
        row2.addView(arrivalCity);

        tableLayout.addView(row2);

        TableRow row3 = new TableRow(context);
        TextView departTime = new TextView(context);
        departTime.setTypeface(departTime.getTypeface(), Typeface.BOLD_ITALIC);
        departTime.setTextSize(12);
        departTime.setText(train.getTrip().getTime());

        TextView duration = new TextView(context);
        duration.setGravity(Gravity.CENTER);
        duration.setText(train.getTrip().getDuration());

        TextView arrivalTime = new TextView(context);
        arrivalTime.setTypeface(arrivalTime.getTypeface(), Typeface.BOLD_ITALIC);
        arrivalTime.setTextSize(12);
        arrivalTime.setText(train.getTrip().getArrivalTime());

        row3.addView(departTime);
        row3.addView(duration);
        row3.addView(arrivalTime);

        tableLayout.addView(row3);

        return tableLayout;
    }
}
