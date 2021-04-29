package com.company;

public class Company {
    String id;
    String name;
    String founding_year;
    String country;
    String networth;

    public Company(String id, String name, String founding_year, String country, String networth) {
        this.id = id;
        this.name = name;
        this.founding_year = founding_year;
        this.country = country;
        this.networth = networth;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", founding_year='" + founding_year + '\'' +
                ", country='" + country + '\'' +
                ", networth='" + networth + '\'' +
                '}';
    }
}
