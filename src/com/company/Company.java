package com.company;

public class Company {
    String id;
    String name;
    String founding_year;
    String country;
    String networth;

    public Company() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFounding_year(String founding_year) {
        this.founding_year = founding_year;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNetworth(String networth) {
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
