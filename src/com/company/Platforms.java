package com.company;

public class Platforms {
    String id;
    String name;
    String createdBy;
    String releaseYear;

    public Platforms(String id, String name, String createdBy, String releaseYear) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Platforms{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }
}
