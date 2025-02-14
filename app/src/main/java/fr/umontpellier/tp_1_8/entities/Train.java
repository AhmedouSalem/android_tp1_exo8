package fr.umontpellier.tp_1_8.entities;

public class Train {
    private String code;
    private Trip trip;

    public Train(String code, Trip trip) {
        this.code = code;
        this.trip = trip;
    }

    public String getCode() {
        return code;
    }

    public Trip getTrip() {
        return trip;
    }
}