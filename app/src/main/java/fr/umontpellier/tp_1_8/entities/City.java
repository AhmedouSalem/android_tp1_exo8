package fr.umontpellier.tp_1_8.entities;

import java.util.List;

public class City {
    private String name;
    private String postalCode;
    private List<String> stations;

    public City(String name, String postalCode, List<String> stations) {
        this.name = name;
        this.postalCode = postalCode;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public List<String> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return name + " (" + postalCode + ")";
    }
}
