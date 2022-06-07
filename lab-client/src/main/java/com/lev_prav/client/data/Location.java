package com.lev_prav.client.data;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Location {
    @CsvBindByName
    private Double x; //Поле не может быть null
    @CsvBindByName
    private Integer y; //Поле не может быть null
    @CsvBindByName
    private Float z; //Поле не может быть null
    @CsvBindByName
    private String name; //Поле может быть null

    public Location(Double x, Integer y, Float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Location() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return x.equals(location.x) && y.equals(location.y) && z.equals(location.z) && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "Location{"
                + "x=" + x
                + ", y=" + y
                + ", z=" + z
                + ", name='" + name + '\''
                + '}';
    }
}
