package fr.umontpellier.tp_1_8.entities;


import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

import java.text.ParseException;

public class Trip {
    private City departureCity;
    private City arrivalCity;
    private String date;
    private String time;
    private String duration;private String arrivalTime;

    public Trip(City departureCity, City arrivalCity, String date, String time, String duration) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.arrivalTime = calculateArrivalTime(time, duration);
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    private String calculateArrivalTime(String departureTime, String duration) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(sdf.parse(departureTime));

            String[] durationParts = duration.split("h");
            int hours = Integer.parseInt(durationParts[0].trim());
            int minutes = Integer.parseInt(durationParts[1].replace("m", "").trim());

            calendar.add(Calendar.HOUR_OF_DAY, hours);
            calendar.add(Calendar.MINUTE, minutes);

            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}