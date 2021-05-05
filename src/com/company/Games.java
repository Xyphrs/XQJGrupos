package com.company;

public class Games {
    String id;
    String companyID;
    String name;
    String developedBy;
    String releaseYear;

    public Games() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDevelopedBy(String developedBy) {
        this.developedBy = developedBy;
    }

    public void setReleaseYear(String releaseYear) {
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
