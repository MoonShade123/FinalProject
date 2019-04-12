package com.htp.domain.to;

import java.util.Objects;

public class Consigment {
    private Long consigmentId;
    private String consigmentName;
    private String countryOfVotion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consigment that = (Consigment) o;
        return Objects.equals(consigmentId, that.consigmentId) &&
                Objects.equals(consigmentName, that.consigmentName) &&
                Objects.equals(countryOfVotion, that.countryOfVotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consigmentId, consigmentName, countryOfVotion);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("\n");
        sb.append("Consigment ID: ");
        sb.append(this.consigmentId);
        sb.append("\n");
        sb.append("Consigment Name: ");
        sb.append(this.consigmentName);
        sb.append("\n");
        sb.append("Country of votion name: ");
        sb.append(this.countryOfVotion);
        sb.append("\n");

        return sb.toString();
    }

    public Long getConsigmentId() {
        return consigmentId;
    }

    public void setConsigmentId(Long consigmentId) {
        this.consigmentId = consigmentId;
    }

    public String getConsigmentName() {
        return consigmentName;
    }

    public void setConsigmentName(String consigmentName) {
        this.consigmentName = consigmentName;
    }

    public String getCountryOfVotion() {
        return countryOfVotion;
    }

    public void setCountryOfVotion(String countryOfVotion) {
        this.countryOfVotion = countryOfVotion;
    }
}
