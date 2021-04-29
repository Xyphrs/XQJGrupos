package com.company;

public class Games {
    String id;
    String companyID;
    String name;
    String developedBy;
    String releaseYear;

    public Games(String id, String companyID, String name, String developedBy, String releaseYear) {
        this.id = id;
        this.companyID = companyID;
        this.name = name;
        this.developedBy = developedBy;
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id='" + id + '\'' +
                ", companyID='" + companyID + '\'' +
                ", name='" + name + '\'' +
                ", developedBy='" + developedBy + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }
}
