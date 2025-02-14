package fr.umontpellier.tp_1_8.data;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import  java.util.Calendar;

import fr.umontpellier.tp_1_8.entities.City;
import fr.umontpellier.tp_1_8.entities.Train;
import fr.umontpellier.tp_1_8.entities.Trip;

public class DataStatic {
    public static List<City> getCitiesFromAPI() {
        List<Train> trains = new ArrayList<Train>();

        List<City> cities = new ArrayList<>();
        List<String> parisStations = new ArrayList<>();
        parisStations.add("Gare du Nord");
        parisStations.add("Gare de Lyon");
        parisStations.add("Gare Saint-Lazare");
        City paris = new City("Paris", "75000", parisStations);
        cities.add(paris);

        List<String> berlinStations = new ArrayList<>();
        berlinStations.add("Berlin Hauptbahnhof");
        berlinStations.add("Berlin Ostbahnhof");
        berlinStations.add("Berlin Südkreuz");
        City berlin = new City("Berlin", "10115", berlinStations);
        cities.add(berlin);

        List<String> madridStations = new ArrayList<>();
        madridStations.add("Atocha");
        madridStations.add("Chamartín");
        madridStations.add("Puerta de Atocha");
        City madrid = new City("Madrid", "28001", madridStations);
        cities.add(madrid);

        List<String> romeStations = new ArrayList<>();
        romeStations.add("Roma Termini");
        romeStations.add("Roma Tiburtina");
        romeStations.add("Roma Ostiense");
        City rome = new City("Rome", "00100", romeStations);
        cities.add(rome);

        return cities;
    }

    public static List<Train> getTrains(List<City> cities) {
        List<Train> trains = new ArrayList<>();

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        trains.add(createTrain(cities.get(0), cities.get(2), "STP004", sdf.format(currentDate.getTime()), "10:00", "1h 50m"));
        trains.add(createTrain(cities.get(1), cities.get(3), "STP005", sdf.format(currentDate.getTime()), "11:30", "2h 45m"));
        trains.add(createTrain(cities.get(3), cities.get(2), "STP006", sdf.format(currentDate.getTime()), "13:00", "3h 00m"));

        currentDate.add(Calendar.DAY_OF_YEAR, 1);
        trains.add(createTrain(cities.get(0), cities.get(1), "STP001", sdf.format(currentDate.getTime()), "12:30", "2h 18m"));

        currentDate.add(Calendar.DAY_OF_YEAR, 1);
        trains.add(createTrain(cities.get(2), cities.get(1), "STP002", sdf.format(currentDate.getTime()), "09:00", "8h 00m"));

        currentDate.add(Calendar.DAY_OF_YEAR, 1);
        trains.add(createTrain(cities.get(3), cities.get(0), "STP003", sdf.format(currentDate.getTime()), "16:00", "5h 30m"));

        return trains;
    }

    private static Train createTrain(City departure, City arrival, String code, String date, String time, String duration) {
        Trip trip = new Trip(departure, arrival, date, time, duration);
        return new Train(code, trip);
    }
}
