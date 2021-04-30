package com.company;

public class Platforms {
    String id;
    String name;
    String createdBy;
    String releaseYear;

    public Platforms() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "\nPlatforms{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }
}
