package com.htp.domain.to;

import java.util.Objects;

public class Country {
    private Integer countryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryId, country.countryId) &&
                Objects.equals(CountryName, country.CountryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, CountryName);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", CountryName='" + CountryName + '\'' +
                '}';
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public Country(Integer countryId, String countryName) {
        this.countryId = countryId;
        CountryName = countryName;
    }

    private String CountryName;
}
